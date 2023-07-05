package cn.goldencis.auxiliary.application.selfcheck;

import cn.goldencis.auxiliary.infrastructure.quartz.QuartzManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SelfCheckManager {
    @Autowired
    QuartzManager quartzManager;

    public void start() {

    }
}
