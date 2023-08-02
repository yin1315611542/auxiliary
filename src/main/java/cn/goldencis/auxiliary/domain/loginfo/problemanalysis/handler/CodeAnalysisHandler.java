package cn.goldencis.auxiliary.domain.loginfo.problemanalysis.handler;

import cn.goldencis.auxiliary.domain.loginfo.ErrorInfo;
import cn.goldencis.auxiliary.domain.loginfo.enumeration.ErrorInfoType;
import cn.goldencis.auxiliary.domain.loginfo.problemanalysis.AbstractAnalysis;
import cn.goldencis.auxiliary.domain.problem.Problem;
import cn.goldencis.auxiliary.domain.loginfo.problemanalysis.AnalysisHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @program: auxiliary
 * @description:
 * @Author: yinhd
 * @create: 2023-06-25 15:50
 **/
@Component
@Slf4j
public class CodeAnalysisHandler extends AbstractAnalysis implements AnalysisHandler {
    @Override
    public void handleError(ErrorInfo errorInfo) {

    }

    @Override
    public Boolean support(ErrorInfo errorInfo) {
        return errorInfo.getMessage().contains("auxiliaryCode");
    }
}
