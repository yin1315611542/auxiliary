package cn.goldencis.auxiliary.infrastructure.reduction;

import cn.goldencis.auxiliary.infrastructure.extract.entity.MyException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @program: auxiliary
 * @description:
 * @Author: yinhd
 * @create: 2023-06-25 15:23
 **/
@Service
@Slf4j
public class ExceptionService {
    public MyException createExceptionFromError(String error) {
        String[] lines = error.split("\\n");
        String message = lines[1];
        String stackTrace = error.substring(error.indexOf("\n", error.indexOf("\n") + 1) + 1);
        Throwable cause = reduction(stackTrace, 0);
        return new MyException(message, cause);
    }

    public Throwable reduction(String stackTrace, int index) {
        if (stackTrace.indexOf("Caused by:", index) == -1) {
            return null;
        } else {
            index = stackTrace.indexOf("Caused by:", index);
            int endIndex = stackTrace.indexOf("Caused by:", index + 1);
            if (endIndex == -1) {
                endIndex = stackTrace.length();
            }
            String causedBy = stackTrace.substring(index, endIndex);
            index = endIndex;
            Throwable causeFromStackTrace = reduction(stackTrace, index);
            return this.createCauseFromStackTrace(causedBy, causeFromStackTrace);
        }
    }

    public MyException createCauseFromStackTrace(String causeStackTrace,Throwable cause) {
        String[] causeLines = causeStackTrace.split("\\n");
        String causeType = causeLines[0].substring("Caused by: ".length());
        String causeMessage = causeLines[1];
        MyException myException = new MyException(causeMessage, cause);
        myException.setType(causeType);
        return myException;
    }
}
