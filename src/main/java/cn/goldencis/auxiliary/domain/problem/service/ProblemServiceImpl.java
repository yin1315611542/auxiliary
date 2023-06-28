package cn.goldencis.auxiliary.domain.problem.service;

import cn.goldencis.auxiliary.infrastructure.extract.entity.MyException;
import cn.goldencis.auxiliary.domain.problem.Problem;
import cn.goldencis.auxiliary.domain.problem.QProblem;
import cn.goldencis.auxiliary.domain.problem.repository.ProblemRepository;
import com.google.common.collect.Lists;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    QProblem problem = QProblem.problem;


    @Override
    public List<Problem> determineProblem(MyException myException) {
        return findProblemByCauseChain(myException.getCauseChain());
    }

    public List<Problem> findProblemByKeyWords(String keyWords){
        return Lists.newArrayList(problemRepository.findAll(problem.keyWord.eq(keyWords)));
    }

    public List<Problem> findProblemByCauseChain(String causeChain){
        return Lists.newArrayList(problemRepository.findAll(problem.causeChain.eq(causeChain)));
    }

}
