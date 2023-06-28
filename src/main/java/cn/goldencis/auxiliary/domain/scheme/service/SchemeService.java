package cn.goldencis.auxiliary.domain.scheme.service;

import cn.goldencis.auxiliary.domain.problem.Problem;
import cn.goldencis.auxiliary.domain.scheme.Scheme;
import cn.goldencis.auxiliary.domain.solution.Solution;
import cn.goldencis.auxiliary.domain.step.Step;
import cn.goldencis.auxiliary.infrastructure.extract.entity.MyException;

import java.util.List;
import java.util.Map;

/**
 * @program: auxiliary
 * @description:
 * @Author: yinhd
 * @create: 2023-06-25 17:40
 **/
public interface SchemeService {
     Scheme execScheme(Solution solution);

     Scheme execScheme(List<Solution> solutions);

     Scheme makeAScheme(Problem problem, Map<Solution,List<Step>> solutions);

     Scheme makeAScheme(MyException myException,Problem problem, Map<Solution,List<Step>> solutions);
}
