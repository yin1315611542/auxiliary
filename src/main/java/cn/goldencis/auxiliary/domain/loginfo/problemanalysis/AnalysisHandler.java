package cn.goldencis.auxiliary.domain.loginfo.problemanalysis;

import cn.goldencis.auxiliary.domain.loginfo.ErrorInfo;

/**
 * @program: auxiliary
 * @description:
 * @Author: yinhd
 * @create: 2023-06-25 15:46
 **/
public interface AnalysisHandler {


    /**
     * 检查是否支持处理此类消息
     *
     * @param errorInfo
     * @return false-不支持，true-支持
     */
     Boolean support(ErrorInfo errorInfo);
    /**
     * 处理消息
     *
     * @param errorInfo
     */
     void  handle(ErrorInfo errorInfo);
}
