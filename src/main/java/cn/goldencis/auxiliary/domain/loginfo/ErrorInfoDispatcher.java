package cn.goldencis.auxiliary.domain.loginfo;

import cn.goldencis.auxiliary.domain.loginfo.problemanalysis.AbstractAnalysis;
import cn.goldencis.auxiliary.domain.loginfo.problemanalysis.AnalysisHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @program: auxiliary
 * @description:
 * @Author: yinhd
 * @create: 2023-06-26 16:03
 **/
@Component
public class ErrorInfoDispatcher {
    @Resource
    LinkedBlockingQueue<ErrorInfo> errorInfos;
    @Autowired
    List<AnalysisHandler> analysisHandlers;

    private void doDispatch(ErrorInfo errorInfo) {
        AnalysisHandler handler = this.getMessageHandler(errorInfo);
        if (handler != null) {
            handler.handle(errorInfo);
        }
    }

    private AnalysisHandler getMessageHandler(ErrorInfo message) {
        for (AnalysisHandler handler : analysisHandlers) {
            if (handler.support(message)) {
                return handler;
            }
        }
        return null;
    }

    //    @PostConstruct
    public void takeErrorInfo() throws InterruptedException {
        while (!errorInfos.isEmpty()){
            ErrorInfo errorInfo = errorInfos.take();
            doDispatch(errorInfo);
        }
    }
}
