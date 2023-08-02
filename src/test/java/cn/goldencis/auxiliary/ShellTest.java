package cn.goldencis.auxiliary;

import cn.goldencis.auxiliary.domain.step.Step;
import cn.goldencis.auxiliary.infrastructure.execution.commandexec.ShellCommandExec;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;
import java.util.Scanner;

@SpringBootTest
public class ShellTest {
    //shell脚本执行文件测试
    @Autowired
    ShellCommandExec shellCommandExec;

    @Test
    public void shellTest() {
        Step step = new Step();
        step.setExcContent("test.sh");
        shellCommandExec.execScheme(step);
    }

    @Test
    public void esSelfCheck(){
        Step step = new Step();
        step.setExcContent("es_selfcheck.sh");
        shellCommandExec.execScheme(step);
    }

    @Test
    public void jh() throws IOException, InterruptedException {
        ProcessBuilder processBuilder = new ProcessBuilder("/var/aux/auxiliary/script/test.sh");
        processBuilder.redirectErrorStream(true);
        Process process = processBuilder.start();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);

                // 如果脚本需要等待用户输入，可以在这里向标准输入流写入数据
                if (line.contains("Please enter your name:")) {
                    Scanner scanner = new Scanner(System.in);
                    String name = scanner.nextLine();
                    writer.write(name);
                    writer.newLine();
                    writer.flush();
                }
            }
        }

        int exitCode = process.waitFor();
    }
}
