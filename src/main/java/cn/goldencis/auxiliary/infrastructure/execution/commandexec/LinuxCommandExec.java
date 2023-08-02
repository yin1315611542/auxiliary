package cn.goldencis.auxiliary.infrastructure.execution.commandexec;

import cn.goldencis.auxiliary.domain.step.Step;
import cn.goldencis.auxiliary.domain.step.enumstep.StepType;
import cn.goldencis.auxiliary.infrastructure.common.CommandUtil;
import cn.goldencis.auxiliary.infrastructure.execution.AbstractExec;
import cn.goldencis.auxiliary.infrastructure.execution.ExecInterface;
import cn.goldencis.auxiliary.infrastructure.execution.entity.ExecResult;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.guieffect.qual.SafeType;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Objects;

/**
 * @program: auxiliary
 * @description:
 * @Author: yinhd
 * @create: 2023-06-27 17:16
 **/
@Component
@Slf4j
public class LinuxCommandExec extends AbstractExec implements ExecInterface {

    @Override
    public ExecResult execScheme(Step step) throws IOException {
        String command = step.getExcContent();
        log.info("【执行命令】: {}", command);
        ExecResult execResult = Objects.requireNonNull(CommandUtil.commandExecute2(command));
        log.info("【执行结果】:{}", execResult.getMessage());
        return execResult;
    }

    @Override
    public Boolean support(Step step) {
        return StepType.Command.equals(step.getStepType());
    }
}
