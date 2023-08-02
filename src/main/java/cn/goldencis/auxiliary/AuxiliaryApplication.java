package cn.goldencis.auxiliary;

import cn.goldencis.auxiliary.application.selfcheck.SelfCheckTaskManager;
import cn.goldencis.auxiliary.domain.deploy.service.HubService;
import cn.goldencis.auxiliary.domain.solution.service.SolutionService;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.SmartLifecycle;

@SpringBootApplication
@Slf4j
public class AuxiliaryApplication implements SmartLifecycle {
    @Autowired
    HubService hubService;
    @Autowired
    SelfCheckTaskManager selfCheckTaskManager;

    private volatile boolean running = false;

    public static void main(String[] args) {
        SpringApplication.run(AuxiliaryApplication.class, args);
    }

    @Override
    public void start() {
        hubService.initHubs();
        //启动自检类型的任务
        selfCheckTaskManager.startSelfCheckTask();
        running = true;
        log.info("===========auxiliary启动完成===========");
    }

    @Override
    public void stop() {
        hubService.saveHub();
        running = false;
        log.info("===========auxiliary关闭完成============");
    }

    @Override
    public boolean isRunning() {
        return running;
    }
}
