package cn.goldencis.auxiliary.application.selfcheck;

import cn.goldencis.auxiliary.infrastructure.quartz.QuartzManager;
import lombok.extern.slf4j.Slf4j;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Slf4j
public class SelfCheckTaskManager {
    @Autowired
    QuartzManager quartzManager;

    @PostConstruct
    private void addSelfCheckTask() {
        try {
            quartzManager.addJob("logSelfCheck", "selfCheck", LogSelfCheckJob.class, "0/10 * * * * ?", null);
            quartzManager.addJob("dailySelfCheck","selfCheck",DailySelfCheckJob.class,"0/10 * * * * ?",null);
        } catch (SchedulerException e) {
            log.info("logSelfCheckJob添加异常{}", e.toString());
        }
    }

    public void startSelfCheckTask() {
        try {
            quartzManager.start();
        } catch (SchedulerException e) {
            log.info("logSelfCheckJob启动异常{}", e.toString());
        }
    }
}
