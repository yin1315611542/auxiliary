package cn.goldencis.auxiliary.application.selfcheck;

import cn.goldencis.auxiliary.domain.deploy.Hub;
import cn.goldencis.auxiliary.domain.loginfo.ErrorInfoDispatcher;
import cn.goldencis.auxiliary.infrastructure.common.DateUtil;
import cn.goldencis.auxiliary.infrastructure.common.SpringUtil;
import cn.goldencis.auxiliary.infrastructure.extract.service.LogExtract;
import lombok.SneakyThrows;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SelfCheckJob implements Job {
    @Autowired
    List<Hub> hubs;

    @SneakyThrows
    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        LogExtract logExtract = SpringUtil.getBean("logExtract");
        String date = DateUtil.YYYY_MM_DD();
        for (Hub hub : hubs) {
            logExtract.extract(hub.getLogPath(), hub.getName() + "-error" + date);
            ErrorInfoDispatcher errorInfoDispatcher = SpringUtil.getBean("errorInfoDispatcher");
            errorInfoDispatcher.takeErrorInfo();
        }
    }
}