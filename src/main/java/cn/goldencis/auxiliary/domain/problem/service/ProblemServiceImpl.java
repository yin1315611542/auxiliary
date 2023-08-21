package cn.goldencis.auxiliary.domain.problem.service;

import cn.goldencis.auxiliary.domain.problem.Problem;
import cn.goldencis.auxiliary.domain.problem.QProblem;
import cn.goldencis.auxiliary.domain.problem.enumtype.ProblemType;
import cn.goldencis.auxiliary.domain.problem.repository.ProblemRepository;
import cn.goldencis.auxiliary.infrastructure.extract.entity.AuxException;
import com.google.common.collect.Lists;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @program: auxiliary
 * @description:
 * @Author: yinhd
 * @create: 2023-06-25 17:19
 **/
@Service
public class ProblemServiceImpl implements ProblemService{
    @Autowired
    ProblemRepository problemRepository;
    @Autowired
    JPAQueryFactory jpaQueryFactory;

    private static final Integer ON = 0; //问题状态 在用

    private static final Integer OFF = 1;//问题状态 废除

    QProblem problem = QProblem.problem;


    @Override
    public List<Problem> determineProblem(AuxException auxException) {
        List<Problem> problems = new ArrayList<>();
        //按照异常链进行查找 此寻找条件比较准确
        problems.addAll(findProblemByCauseChain(auxException.getCauseChain()));
        //按照关键字进行查找 关键字的选取要谨慎
        problems.addAll(findProblemByKeyWords(auxException));
        //问题去重
        return problems.stream()
                .map(Problem::getId) // 使用 map 方法将 Person 转换为其 id 属性
                .distinct() // 去除重复的 id 属性
                .map(id -> problems.stream().filter(problem -> problem.getId().equals(id)).findFirst().get()) // 将去重后的 id 属性转换回 Person 对象
                .filter(problem->problem.getStatus().equals(ON))
                .collect(Collectors.toList());
    }

    public List<Problem> findProblemByKeyWords(AuxException auxException) {
        return problemRepository.findAll().stream().filter(ex -> !ObjectUtils.isEmpty(ex.getKeyWord())).filter(ex -> auxException.getCause().toString().contains(ex.getKeyWord())).collect(Collectors.toList());
    }

    public List<Problem> findProblemByCauseChain(String causeChain){
        return Lists.newArrayList(problemRepository.findAll(problem.causeChain.eq(causeChain)));
    }

    public List<Problem> finSelfCheckProblem(){
        return Lists.newArrayList(problemRepository.findAll(problem.problemType.eq(ProblemType.SelfCheckProblem).and(problem.status.eq(ON))));
    }

}
