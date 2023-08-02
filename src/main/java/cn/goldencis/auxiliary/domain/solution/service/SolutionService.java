package cn.goldencis.auxiliary.domain.solution.service;

import cn.goldencis.auxiliary.domain.problem.Problem;
import cn.goldencis.auxiliary.domain.solution.Solution;

import java.util.List;

/**
 * @program: auxiliary
 * @description:
 * @Author: yinhd
 * @create: 2023-06-25 17:43
 **/
public interface SolutionService {
    //确定方案
    List<Solution> determineSolution(Problem problem);

    List<Solution> determineSolution(List<Problem> problems);
}
