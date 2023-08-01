package cn.goldencis.auxiliary.application.errorfix;

import cn.goldencis.auxiliary.domain.problem.Problem;
import cn.goldencis.auxiliary.domain.problem.service.ProblemService;
import cn.goldencis.auxiliary.domain.scheme.Scheme;
import cn.goldencis.auxiliary.domain.scheme.handler.SchemeHandler;
import cn.goldencis.auxiliary.domain.scheme.service.SchemeService;
import cn.goldencis.auxiliary.domain.solution.Solution;
import cn.goldencis.auxiliary.domain.solution.service.SolutionService;
import cn.goldencis.auxiliary.domain.step.Step;
import cn.goldencis.auxiliary.domain.step.service.StepService;
import cn.goldencis.auxiliary.infrastructure.extract.entity.AuxException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: auxiliary
 * @description:
 * @Author: yinhd
 * @create: 2023-06-26 14:55
 **/
@Service
public class ErrorFixService {
    @Autowired
    ProblemService problemService;
    @Autowired
    SolutionService solutionService;
    @Autowired
    SchemeService schemeService;
    @Autowired
    StepService stepService;
    @Autowired
    SchemeHandler schemeHandler;

    public void fix(AuxException exception) {
        //定位问题
        List<Problem> problems = problemService.determineProblem(exception);
        for (Problem problem : problems) {
            //形成方案
            Scheme scheme = schemeService.makeAScheme(exception, problem,  generateSolutionMap(problem));
            //执行方案
            schemeHandler.ImplementScheme(scheme);
        }
    }


    public Boolean fix(Problem problem){
        Scheme scheme = schemeService.makeAScheme(null, problem,  generateSolutionMap(problem));
        schemeHandler.ImplementScheme(scheme);
        return true;
    }

    public Map<Solution,List<Step>> generateSolutionMap(Problem problem){
        Map<Solution,List<Step> > solutionMap = new HashMap<>();
        //确定解决方案
        List<Solution> solutions = solutionService.determineSolution(problem);
        //生成执行方案
        for (Solution solution : solutions) {
            List<Step> steps = stepService.finStepBySolutionId(solution.getId());
            solutionMap.put(solution, steps);
        }
        return solutionMap;
    }
}
