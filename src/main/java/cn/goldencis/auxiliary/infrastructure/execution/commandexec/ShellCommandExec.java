package cn.goldencis.auxiliary.infrastructure.execution.commandexec;

import cn.goldencis.auxiliary.domain.step.Step;
import cn.goldencis.auxiliary.infrastructure.common.CommandUtil;
import cn.goldencis.auxiliary.infrastructure.execution.AbstractExec;
import cn.goldencis.auxiliary.infrastructure.execution.ExecInterface;
import cn.goldencis.auxiliary.infrastructure.execution.entity.ExecResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * @program: auxiliary
 * @description:
 * @Author: yinhd
 * @create: 2023-06-27 17:16
 **/
@Component
@Slf4j
public class ShellCommandExec extends AbstractExec implements ExecInterface {
    @Value("${auxiliary.sh.path}")
    String path;

    @Override
    public ExecResult execScheme(Step step) {
        String shName = step.getExcContent();
        String bash = path + File.separator + shName;  // 指定要执行的shell脚本文件路径
        log.info("【执行脚本路径】:{}", bash);
        ExecResult execResult = CommandUtil.bashExecute(bash);
        if (execResult != null) {
            log.info("【脚本执行结果】:{}", execResult.getMessage());
        }
        return execResult;
    }

    @Override
    public Boolean support(Step step) {
        return false;
    }
}
