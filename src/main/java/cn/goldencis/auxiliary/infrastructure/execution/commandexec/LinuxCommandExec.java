package cn.goldencis.auxiliary.infrastructure.execution.commandexec;

import cn.goldencis.auxiliary.domain.step.Step;
import cn.goldencis.auxiliary.infrastructure.common.CommandUtil;
import cn.goldencis.auxiliary.infrastructure.execution.AbstractExec;
import cn.goldencis.auxiliary.infrastructure.execution.ExecInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

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
    public void execScheme(Step step) {
        String command = step.getExcContent();
        log.info("【Command】执行命令: {}", command);
        String execute = CommandUtil.commandExecute(command);
        log.info("【Command】执行结果:{}", execute);

    }

    @Override
    public Boolean support(Step step) {
        return true;
    }
}
