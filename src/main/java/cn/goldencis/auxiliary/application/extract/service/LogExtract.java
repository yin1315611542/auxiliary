package cn.goldencis.auxiliary.application.extract.service;

import cn.goldencis.auxiliary.application.extract.entity.MyException;
import cn.goldencis.auxiliary.infrastructure.common.CommandUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @program: auxiliary
 * @description:
 * @Author: yinhd
 * @create: 2023-06-21 11:41
 **/
public class LogExtract {
    public List<MyException> extract(String file){
        String logContent = CommandUtil.execute("/gdsoft/soft/vops/logs/"+file, null);
        Pattern errorPattern = Pattern.compile("^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2},\\d+ ERROR.*?\\n(.*\\n)*?(?=^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2},\\d+ ERROR|$)", Pattern.MULTILINE);
        List<MyException> exceptionList = new ArrayList<>();
        Matcher matcher = errorPattern.matcher(logContent);
        while (matcher.find()) {
            String error = matcher.group();
            MyException exception = this.createExceptionFromError(error);
            exceptionList.add(exception);
        }
        return exceptionList;
    }
    private MyException createExceptionFromError(String error) {
        String[] lines = error.split("\\n");
        String message = lines[0];
        String stackTrace = error.substring(error.indexOf("\n", error.indexOf("\n") + 1) + 1);
        Throwable cause = createCauseFromStackTrace(stackTrace);
        MyException exception = new MyException(message, cause);
        return exception;
    }

    private static Throwable createCauseFromStackTrace(String stackTrace) {
        if (stackTrace.startsWith("\tat ")) {
            return null;
        }
        int causeIndex = stackTrace.lastIndexOf("Caused by: ");
        if (causeIndex < 0) {
            return null;
        }
        String causeStackTrace = stackTrace.substring(causeIndex);
        String[] causeLines = causeStackTrace.split("\\n");
        String causeType = causeLines[0].substring("Caused by: ".length());
        String causeMessage = causeLines[1];
        String causeStackTraceWithoutHeader = causeStackTrace.substring(causeStackTrace.indexOf("\n", causeStackTrace.indexOf("\n") + 1) + 1);
        Throwable cause = createCauseFromStackTrace(causeStackTraceWithoutHeader);
        MyException causeException = new MyException(causeMessage, cause);
        causeException.setType(causeType);
        return causeException;
    }

}
