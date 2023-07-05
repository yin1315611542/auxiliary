package cn.goldencis.auxiliary.infrastructure.extract.service;

import cn.goldencis.auxiliary.domain.loginfo.ErrorInfo;
import cn.goldencis.auxiliary.infrastructure.common.CommandUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.File;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @program: auxiliary
 * @description:
 * @Author: yinhd
 * @create: 2023-06-21 11:41
 **/
@Component
@Slf4j
public class LogExtract {
    @Resource
    LinkedBlockingQueue<ErrorInfo> errorInfos;
    public static String interceptionRules = "^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2},\\d+ ERROR.*?\\n(.*\\n)*?(?=^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2},\\d+ ERROR|$)";
    public static String interceptionMethod = "cat";

    public void extract(String path, String file) {
        String logContent = CommandUtil.commandExecute(interceptionMethod + " " + path + File.separator + file);
        if (logContent != null) {
            log.info("【日志读取长度】:{}", logContent.length());
        }
        Pattern errorPattern = Pattern.compile(interceptionRules, Pattern.MULTILINE);
        if (logContent != null) {
            Matcher matcher = errorPattern.matcher(logContent);
            while (matcher.find()) {
                String error = matcher.group();
                errorInfos.add(new ErrorInfo(error, null));
            }
        }
    }
}
