package cn.goldencis.auxiliary.application.selfcheck;

import cn.goldencis.auxiliary.infrastructure.quartz.QuartzManager;
import lombok.extern.slf4j.Slf4j;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Slf4j
public class SelfCheckManager {
    @Autowired
    QuartzManager quartzManager;

    @PostConstruct
    public void add() {
        try {
            quartzManager.addJob("logSelfCheck", "selfCheck", SelfCheckJob.class, "10/10 * * * * ?", null);
            quartzManager.start();
        } catch (SchedulerException e) {
            log.info("logSelfCheckJob添加异常{}", e.toString());
        }
    }
}
