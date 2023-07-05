package cn.goldencis.auxiliary;

import cn.goldencis.auxiliary.domain.step.Step;
import cn.goldencis.auxiliary.infrastructure.execution.commandexec.ShellCommandExec;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
}
