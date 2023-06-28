package cn.goldencis.auxiliary.domain.problem.service;

import cn.goldencis.auxiliary.infrastructure.extract.entity.MyException;
import cn.goldencis.auxiliary.domain.problem.Problem;

import java.util.List;

/**
 * @program: auxiliary
 * @description:
 * @Author: yinhd
 * @create: 2023-06-25 17:18
 **/
public interface ProblemService {
    //确定问题
     List<Problem> determineProblem(MyException myException);

     List<Problem> findProblemByKeyWords(String keyWords);

     List<Problem> findProblemByCauseChain(String causeChain);


}
