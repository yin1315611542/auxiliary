package cn.goldencis.auxiliary;

import cn.goldencis.auxiliary.application.errorfix.ErrorFixService;
import cn.goldencis.auxiliary.infrastructure.extract.entity.AuxException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ExceptionTest {
    @Autowired
    ErrorFixService errorFixService;

    @Test
    public void test1() {
        AuxException auxException = new AuxException();
        auxException.setCauseChain("11");
        errorFixService.fix(auxException);
    }

    @Test
    public void test() {
        AuxException auxException = new AuxException();
        auxException.setCauseChain("22");
        errorFixService.fix(auxException);
    }

    @Test
    public void test3() {
        AuxException auxException = new AuxException();
        auxException.setCauseChain("33");
        auxException.setCause(new Exception("key"));
        errorFixService.fix(auxException);
    }
}
