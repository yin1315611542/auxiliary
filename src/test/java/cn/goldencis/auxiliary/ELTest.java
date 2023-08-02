package cn.goldencis.auxiliary;

import cn.goldencis.auxiliary.infrastructure.common.ELUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ELTest {
    @Test
    public void equals() {
        boolean result = ELUtils.evaluate("");
    }
}
