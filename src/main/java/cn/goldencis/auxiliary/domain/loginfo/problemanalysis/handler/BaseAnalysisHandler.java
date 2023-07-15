package cn.goldencis.auxiliary.domain.loginfo.problemanalysis.handler;

import cn.goldencis.auxiliary.application.errorfix.ErrorFixService;
import cn.goldencis.auxiliary.domain.loginfo.ErrorInfo;
import cn.goldencis.auxiliary.domain.loginfo.problemanalysis.AbstractAnalysis;
import cn.goldencis.auxiliary.domain.loginfo.problemanalysis.AnalysisHandler;
import cn.goldencis.auxiliary.infrastructure.extract.entity.AuxException;
import cn.goldencis.auxiliary.infrastructure.reduction.ExceptionService;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @program: auxiliary
 * @description:
 * @Author: yinhd
 * @create: 2023-06-25 15:49
 **/
@Component
@Slf4j
public class BaseAnalysisHandler extends AbstractAnalysis implements AnalysisHandler {

    @Autowired
    ExceptionService exceptionService;
    @Autowired
    JPAQueryFactory jpaQueryFactory;
    @Autowired
    ErrorFixService errorFixService;
    @Override
    public void handleError(ErrorInfo errorInfo) {
        AuxException auxException = exceptionService.createExceptionFromError(errorInfo.getMessage());
        errorFixService.fix(auxException);
    }

    @Override
    public Boolean support(ErrorInfo errorInfo) {
        return true;
    }
}
