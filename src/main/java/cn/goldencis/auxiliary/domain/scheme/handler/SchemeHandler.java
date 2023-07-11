package cn.goldencis.auxiliary.domain.scheme.handler;

import cn.goldencis.auxiliary.domain.scheme.Scheme;
import cn.goldencis.auxiliary.domain.solution.Solution;
import cn.goldencis.auxiliary.domain.step.Step;
import cn.goldencis.auxiliary.infrastructure.execution.ExecDispatcher;
import cn.goldencis.auxiliary.infrastructure.execution.entity.ExecResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@Slf4j
public class SchemeHandler {
    @Autowired
    ExecDispatcher dispatcher;

    public void ImplementScheme(Scheme scheme) {
        scheme.init();
        Map<Solution, Step> solutions = scheme.getSteps();
        for (Solution solution : solutions.keySet()) {
            log.info("【执行方案】：{}", solution.getName());
            boolean result = execStepOfSolution(solutions.get(solution));
            if (result) {
                break;
            }
        }
    }

    public boolean execStepOfSolution(Step step) {
        log.info("--【执行步骤】：{}", step.getStepName());
        ExecResult execResult = dispatcher.doDispatch(step);
        if (execResult.getCode() == 1) {
            return false;
        } else {
            execStep(step.getChildStep());
        }
        return true;
    }

    public boolean execStep(List<Step> steps) {
        for (Step step : steps) {
            ExecResult execResult = dispatcher.doDispatch(step);
            if (execResult.getCode() == 0) {
                return execStep(step.getChildStep());
            }
        }
        return false;
    }
}
