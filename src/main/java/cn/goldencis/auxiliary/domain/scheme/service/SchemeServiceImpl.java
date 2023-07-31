package cn.goldencis.auxiliary.domain.scheme.service;

import cn.goldencis.auxiliary.domain.problem.Problem;
import cn.goldencis.auxiliary.domain.scheme.Scheme;
import cn.goldencis.auxiliary.domain.solution.Solution;
import cn.goldencis.auxiliary.domain.step.Step;
import cn.goldencis.auxiliary.infrastructure.extract.entity.AuxException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @program: auxiliary
 * @description:
 * @Author: yinhd
 * @create: 2023-06-25 17:41
 **/
@Service
@Slf4j
public class SchemeServiceImpl implements SchemeService{

    @Override
    public Scheme execScheme(Solution solution) {
        return null;
    }

    @Override
    public Scheme execScheme(List<Solution> solutions) {
        return null;
    }

    @Override
    public Scheme makeAScheme(Problem problem, Map<Solution,List<Step>> solutions) {
        return new Scheme(problem,solutions);
    }

    @Override
    public Scheme makeAScheme(AuxException auxException, Problem problem, Map<Solution, List<Step>> solutions) {
        return new Scheme(auxException, problem, solutions);
    }
}
