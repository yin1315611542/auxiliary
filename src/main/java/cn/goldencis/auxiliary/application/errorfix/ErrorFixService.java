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
        Map<Solution,List<Step> > solutionMap = new HashMap<>();
        //遍历问题，确定方案
        for (Problem problem : problems) {
            //确定解决方案
            List<Solution> solutions = solutionService.determineSolution(problem);
            //生成执行方案
            for (Solution solution : solutions) {
                List<Step> steps = stepService.finStepBySolutionId(solution.getId());
                solutionMap.put(solution, steps);
            }
            Scheme scheme = schemeService.makeAScheme(exception, problem, solutionMap);
            //执行方案
            schemeHandler.ImplementScheme(scheme);
        }
    }


    public Boolean fix(){
        //读取日志
        //定位问题
        //确定解决方案
        //执行解决方案
        return null;
    }
}
