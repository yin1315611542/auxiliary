package cn.goldencis.auxiliary.domain.loginfo.problemanalysis;

import cn.goldencis.auxiliary.domain.loginfo.ErrorInfo;
import lombok.extern.slf4j.Slf4j;

/**
 * @program: auxiliary
 * @description:
 * @Author: yinhd
 * @create: 2023-06-25 16:05
 **/
@Slf4j
public abstract class AbstractAnalysis implements AnalysisHandler{
    public final void handle(ErrorInfo errorInfo){
       if (!support(errorInfo)){
         log.error("handler do not support the error");
         return;
       }
       handleError(errorInfo);
    }
    /**
     * 校验后处理消息
     * @param errorInfo
     */
    public abstract void handleError(ErrorInfo errorInfo);
}
