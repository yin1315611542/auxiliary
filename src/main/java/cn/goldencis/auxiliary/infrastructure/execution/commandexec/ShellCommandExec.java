package cn.goldencis.auxiliary.infrastructure.execution.commandexec;

import cn.goldencis.auxiliary.domain.step.Step;
import cn.goldencis.auxiliary.infrastructure.execution.AbstractExec;
import cn.goldencis.auxiliary.infrastructure.execution.ExecInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

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
    public void execScheme(Step step) {
        try {
            String shName = step.getExcContent();
            // 指定要执行的shell脚本文件路径
            String command = path + File.separator + shName;
            // 创建一个ProcessBuilder对象，设置要执行的命令
            ProcessBuilder processBuilder = new ProcessBuilder(command);
            // 启动进程并等待其完成
            Process process = processBuilder.start();
            boolean exitCode = process.waitFor(10L, TimeUnit.SECONDS);
            // 读取进程的输出
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            StringBuffer result = new StringBuffer();
            while ((line = reader.readLine()) != null) {
                // 处理进程输出
                result.append("\n").append(line);
            }
            log.info("【执行脚本】result:{}", result);
            // 检查进程是否正常结束
            if (exitCode) {
                log.info("【执行脚本】successful!");
            } else {
                log.info("【执行脚本】failure!");
            }
        } catch (Exception e) {
            log.info("【脚本执行】Exception:{}", e.toString());
        }
    }

    @Override
    public Boolean support(Step step) {
        return false;
    }
}
