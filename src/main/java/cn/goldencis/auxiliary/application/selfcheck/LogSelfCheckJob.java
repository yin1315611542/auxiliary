package cn.goldencis.auxiliary.application.selfcheck;

import cn.goldencis.auxiliary.domain.deploy.Hub;
import cn.goldencis.auxiliary.domain.loginfo.ErrorInfoDispatcher;
import cn.goldencis.auxiliary.infrastructure.common.DateUtil;
import cn.goldencis.auxiliary.infrastructure.common.SpringUtil;
import cn.goldencis.auxiliary.infrastructure.extract.service.LogExtract;
import lombok.SneakyThrows;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.util.ObjectUtils;

import java.util.List;


public class LogSelfCheckJob implements Job {

    @SneakyThrows
    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        LogExtract logExtract = SpringUtil.getBean("logExtract");
        List<Hub> hubs = SpringUtil.getBean("hubs");
        String date = DateUtil.YYYY_MM_DD();
        for (Hub hub : hubs) {
            String markTime = logExtract.extract(hub.getLogPath(), hub.getName() + "-error." + date + ".log", hub.getLogExtractTime());
            if (!ObjectUtils.isEmpty(markTime)) {
                hub.setLogExtractTime(markTime);
            }
            ErrorInfoDispatcher errorInfoDispatcher = SpringUtil.getBean("errorInfoDispatcher");
            errorInfoDispatcher.takeErrorInfo();
        }
    }
}