package cn.goldencis.auxiliary;

import cn.goldencis.auxiliary.domain.deploy.service.HubService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.SmartLifecycle;

@SpringBootApplication
@Slf4j
public class AuxiliaryApplication implements SmartLifecycle {
    @Autowired
    HubService hubService;

    private volatile boolean running = false;

    public static void main(String[] args) {
        SpringApplication.run(AuxiliaryApplication.class, args);
    }

    @Override
    public void start() {
        hubService.initHubs();
        running = true;
        log.info("===========容器启动完成===========");
    }

    @Override
    public void stop() {
        hubService.saveHub();
        running = false;
        log.info("===========容器关闭完成============");
    }

    @Override
    public boolean isRunning() {
        return running;
    }
}
