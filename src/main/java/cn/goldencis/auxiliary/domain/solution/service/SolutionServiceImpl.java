package cn.goldencis.auxiliary.domain.solution.service;

import cn.goldencis.auxiliary.domain.problem.Problem;
import cn.goldencis.auxiliary.domain.solution.QSolution;
import cn.goldencis.auxiliary.domain.solution.Solution;
import cn.goldencis.auxiliary.domain.solution.repository.SolutionRepository;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;

/**
 * @program: auxiliary
 * @description:
 * @Author: yinhd
 * @create: 2023-06-25 17:43
 **/
@Service
public class SolutionServiceImpl implements SolutionService{
    QSolution qSolution = QSolution.solution;
    @Autowired
    SolutionRepository solutionRepository;
    @Override
    public List<Solution> determineSolution(Problem problem) {
        return  Lists.newArrayList(solutionRepository.findAll(qSolution.problemId.eq(problem
                .getId())));
    }

    @Override
    public List<Solution> determineSolution(List<Problem> problems) {
        return null;
    }
}
