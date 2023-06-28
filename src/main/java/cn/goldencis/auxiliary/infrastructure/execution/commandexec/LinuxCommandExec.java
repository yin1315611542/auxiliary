package cn.goldencis.auxiliary.infrastructure.execution.commandexec;

import cn.goldencis.auxiliary.domain.loginfo.problemanalysis.AnalysisHandler;
import cn.goldencis.auxiliary.domain.scheme.Scheme;
import cn.goldencis.auxiliary.domain.solution.Solution;
import cn.goldencis.auxiliary.domain.step.Step;
import cn.goldencis.auxiliary.infrastructure.common.CommandUtil;
import cn.goldencis.auxiliary.infrastructure.execution.AbstractExec;
import cn.goldencis.auxiliary.infrastructure.execution.ExecInterface;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.exec.CommandLine;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

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
        log.info("【ExecCommand】LinuxCommand: {}",command);
        String execute = CommandUtil.execute("",command);
        log.info("【ExecCommand】CommandResult:{}",execute);

    }

    @Override
    public Boolean support(Step step) {
        return true;
    }
}
