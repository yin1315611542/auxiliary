package cn.goldencis.auxiliary.infrastructure.execution;

import cn.goldencis.auxiliary.domain.step.Step;
import cn.goldencis.auxiliary.infrastructure.execution.entity.ExecResult;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * @program: auxiliary
 * @description:
 * @Author: yinhd
 * @create: 2023-06-28 09:36
 **/
@Slf4j
public  abstract class AbstractExec implements ExecInterface{
    public final ExecResult exec(Step step) {
        try {
            return execScheme(step);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public abstract ExecResult execScheme(Step step) throws IOException;
}
