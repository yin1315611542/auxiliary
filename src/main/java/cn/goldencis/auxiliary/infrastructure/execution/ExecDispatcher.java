package cn.goldencis.auxiliary.infrastructure.execution;

import cn.goldencis.auxiliary.domain.scheme.Scheme;
import cn.goldencis.auxiliary.domain.solution.Solution;
import cn.goldencis.auxiliary.domain.step.Step;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @program: auxiliary
 * @description:
 * @Author: yinhd
 * @create: 2023-06-28 10:07
 **/
@Component
@Slf4j
public class ExecDispatcher {
    @Autowired
    List<ExecInterface> execInterfaces;

    private void doDispatch(Step step){
        ExecInterface execHandler = this.getExecHandler(step);
        if (execHandler!=null){
            execHandler.exec(step);
        }
    }

    private ExecInterface getExecHandler(Step step){
        for (ExecInterface handler: execInterfaces) {
            if (handler.support(step)){
                return handler;
            }
        }
        return null;
    }

    public void execScheme(Scheme scheme){
        Map<Solution, List<Step>> solutions = scheme.getSolutions();
        for (Solution solution: solutions.keySet()) {
            log.info("【执行方案】：{}", solution.getName());
               List<Step> steps = solutions.get(solution);
               for (Step step: steps) {
                   this.doDispatch(step);
               }
        }
    }
}
