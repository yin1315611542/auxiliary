package cn.goldencis.auxiliary.infrastructure.extract.service;

import cn.goldencis.auxiliary.domain.loginfo.ErrorInfo;
import cn.goldencis.auxiliary.infrastructure.common.CommandUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.io.File;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @program: auxiliary
 * @description: 日志截取规则：每次截取5000行日志，并且记录时间，下次截取从记录的时间开始
 * @Author: yinhd
 * @create: 2023-06-21 11:41
 **/
@Component
@Slf4j
public class LogExtract {
    @Resource
    LinkedBlockingQueue<ErrorInfo> errorInfos;
    public static String interceptionRules = "^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2},\\d+ ERROR.*?\\n(.*\\n)*?(?=^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2},\\d+ ERROR|$)";
    public static String interceptionRules2 = "^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2},\\d+ ERROR.*?\\n(.*\\n)*?(?=^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2},\\d+ ERROR|$).*?";
    public static Integer interceptionLength = 500;
    public static String interceptionMethod1 = "head -n %s %s";    //用于从文件头开始截取日志
    public static String interceptionMethod2 = "grep -A %s \"%s\" %s"; //用于基于上次截取的时间截取日志

    public String extract(String path, String file, String extractTime) {
        //选择截取命令
        String command = ObjectUtils.isEmpty(extractTime) ? String.format(interceptionMethod1, interceptionLength, path + File.separator + file)
                : String.format(interceptionMethod2, interceptionLength, extractTime, path + File.separator + file);
        //执行命令
        Map<String, String> info = CommandUtil.commandExecute(command);
        String logContent = Objects.requireNonNull(info).get("context");
        log.info("【日志读取】命令：【{}】, 文件：【{}】, 截取起始时间:【{}】, 长度【{}】", command, file, extractTime, ObjectUtils.isEmpty(logContent) ? 0 : logContent.length());
        //分割错误日志
        if (!ObjectUtils.isEmpty(logContent)) {
            Matcher matcher = Pattern.compile(interceptionRules, Pattern.MULTILINE).matcher(logContent);
            String mark = null;
            while (matcher.find()) {
                ErrorInfo errorInfo = addErrorInfo(matcher.group(), extractTime);
                mark = ObjectUtils.isEmpty(errorInfo) ? null : errorInfo.getOccurrenceTime();
            }
            if (ObjectUtils.isEmpty(mark) && Integer.parseInt(Objects.requireNonNull(info).get("lineNum")) < 200) {
                ErrorInfo errorInfo = addSingleErrorInfo(logContent, extractTime);
                mark = ObjectUtils.isEmpty(errorInfo) ? null : errorInfo.getOccurrenceTime();
            }
            return mark;
        }
        return null;
    }

    public ErrorInfo addErrorInfo(String content, String extractTime) {
        if (ObjectUtils.isEmpty(content) || content.equals("\n")) {
            return null;
        }
        ErrorInfo errorInfo = new ErrorInfo(content, null).init();
        String occurrenceTime = errorInfo.getOccurrenceTime();
        if (!Objects.equals(occurrenceTime, extractTime)) {
            errorInfos.add(errorInfo);
            return errorInfo;
        }
        return null;
    }

    public ErrorInfo addSingleErrorInfo(String content, String extractTime) {
        int index = 0;
        String regex = "\\d{4}-\\d{2}-\\d{2}\\s\\d{2}:\\d{2}:\\d{2},\\d{3}\\sERROR";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            int startIndex = matcher.start();
            addErrorInfo(content.substring(index, startIndex), extractTime);
            index = startIndex;
        }
        return addErrorInfo(content.substring(index), extractTime);
    }


}
