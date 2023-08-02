package cn.goldencis.auxiliary.infrastructure.execution;

import cn.goldencis.auxiliary.domain.step.Step;
import cn.goldencis.auxiliary.infrastructure.execution.entity.ExecResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

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

    public ExecResult doDispatch(Step step) {
        ExecInterface execHandler = this.getExecHandler(step);
        if (execHandler!=null){
            return execHandler.exec(step);
        }
        throw new RuntimeException("【"+step.getStepName()+"】"+"未匹配到此步骤的步骤类型，请检查stepType字段是否正确");

    }

    private ExecInterface getExecHandler(Step step){
        for (ExecInterface handler: execInterfaces) {
            if (handler.support(step)){
                return handler;
            }
        }
        return null;
    }
}
