package cn.goldencis.auxiliary;

import cn.goldencis.auxiliary.application.errorfix.ErrorFixService;
import cn.goldencis.auxiliary.infrastructure.extract.entity.MyException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ExceptionTest {
    @Autowired
    ErrorFixService errorFixService;

    @Test
    public void test1() {
        MyException myException = new MyException();
        myException.setCauseChain("11");
        errorFixService.fix(myException);
    }
}
