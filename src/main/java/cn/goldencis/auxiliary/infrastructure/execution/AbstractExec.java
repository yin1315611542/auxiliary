package cn.goldencis.auxiliary.infrastructure.execution;

import cn.goldencis.auxiliary.domain.step.Step;
import lombok.extern.slf4j.Slf4j;

/**
 * @program: auxiliary
 * @description:
 * @Author: yinhd
 * @create: 2023-06-28 09:36
 **/
@Slf4j
public  abstract class AbstractExec implements ExecInterface{
    public  final void exec(Step step){
        if (!support(step)){
            log.error("handler do not support the error");
            return;
        }
        execScheme(step);
    }

    public abstract void execScheme(Step step);
}
