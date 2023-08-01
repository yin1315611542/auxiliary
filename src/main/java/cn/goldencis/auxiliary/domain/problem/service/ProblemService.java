package cn.goldencis.auxiliary.domain.problem.service;

import cn.goldencis.auxiliary.domain.problem.Problem;
import cn.goldencis.auxiliary.infrastructure.extract.entity.AuxException;

import java.util.List;

/**
 * @program: auxiliary
 * @description:
 * @Author: yinhd
 * @create: 2023-06-25 17:18
 **/
public interface ProblemService {
    /**
     * 根据异常确定问题
     * @param auxException
     */
    List<Problem> determineProblem(AuxException auxException);

    List<Problem> findProblemByKeyWords(AuxException auxException);

    List<Problem> findProblemByCauseChain(String causeChain);

    /**
     * 查询自检类问题
     */
    List<Problem> finSelfCheckProblem();


}
