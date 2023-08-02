package cn.goldencis.auxiliary.infrastructure.common;

import cn.goldencis.auxiliary.infrastructure.execution.entity.ExecResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.Executor;
import org.apache.commons.exec.PumpStreamHandler;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @program: auxiliary
 * @description:
 * @Author: yinhd
 * @create: 2023-06-21 13:59
 **/
@Slf4j
public class CommandUtil {
    @Deprecated
    public static String execute(String command) {
        try {
            CommandLine cmdLine = CommandLine.parse(command);
            Executor executor = new DefaultExecutor();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            PumpStreamHandler streamHandler = new PumpStreamHandler(outputStream);
            executor.setStreamHandler(streamHandler);
            executor.execute(cmdLine);
            return outputStream.toString();
        } catch (Exception e) {
            log.info("【日志抽取异常】:{}", e.toString());
        }
        return null;
    }

    public static ExecResult commandExecute2(String command) {
        String[] cmd = new String[]{"bash", "-c", command};
        try {
            Process process = Runtime.getRuntime().exec(cmd);
            // 读取进程的输出
            ExecResult result = getResult(process);
            int code = process.waitFor();
            if (code == 0) {
                return result.setCode(0);
            } else {
                return result.setCode(1);
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Error while executing command", e);
        }
    }

    public static ExecResult commandExecute(String command) {
        try {
            String[] cmd = new String[]{"bash", "-c", command};
            Process process = Runtime.getRuntime().exec(cmd);
            InputStreamReader inputStreamReader = new InputStreamReader(process.getInputStream());
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line;
            int lineNum = 0;
            ExecResult execResult = new ExecResult();
            while ((line = reader.readLine()) != null) {
                execResult.getMessage().append(line).append("\n");
                lineNum++;
            }
            process.getInputStream().close();
            inputStreamReader.close();
            reader.close();
            return execResult.setCode(0).setLineNumber(lineNum);
        } catch (IOException e) {
            log.info("command2执行命令异常{}", e.toString());
        }
        return null;
    }

    public static ExecResult bashExecute(String bash) {
        try {
            // 创建一个ProcessBuilder对象，设置要执行的命令
            ProcessBuilder processBuilder = new ProcessBuilder(bash);
            // 启动进程并等待其完成
            Process process = processBuilder.start();
            // 读取进程的输出
            ExecResult result = getResult(process);

            int code = process.waitFor();
            if (code == 0) {
                return result.setCode(0);
            } else {
                return result.setCode(1);
            }
        } catch (Exception e) {
            log.info("【脚本】:{}执行Exception:{}", bash, e.toString());
        }
        return null;
    }

    private static ExecResult getResult(Process process) throws IOException {
        String line;
        ExecResult execResult = new ExecResult();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream())); BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()), 1024 * 1024)) {
            while ((line = reader.readLine()) != null) {
                execResult.getMessage().append(line);
            }
            while ((line = errorReader.readLine()) != null) {
                execResult.getErrorMessage().append(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            process.getInputStream().close();
            process.getErrorStream().close();
        }
        return execResult;
    }

    @Deprecated
    public static String execute2(String command) {
        try {
            List<String> splits = splits(command);
            String[] strings = listToStringArray(splits);
            Process process = Runtime.getRuntime().exec(strings);
            process.waitFor();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                log.info(line);
            }
            reader.close();
        } catch (IOException | InterruptedException e) {
            log.info("command2执行命令异常{}", e.toString());
        }
        return null;
    }

    public static List<String> splits(String text) {
        List<String> result = new ArrayList<>();
        Pattern pattern = Pattern.compile("\"(.*?)\"");
        Matcher matcher = pattern.matcher(text);
        int lastIndex = 0;
        while (matcher.find()) {
            int matchStart = matcher.start();
            int matchEnd = matcher.end();
            if (lastIndex < matchStart) {
                // 将两个匹配之间的非匹配文本添加到结果中
                String nonMatchText = text.substring(lastIndex, matchStart);
                result.add(nonMatchText);
            }
            // 将匹配的文本添加到结果中
            String quotedText = text.substring(matchStart, matchEnd);
            result.add(quotedText);
            lastIndex = matchEnd;
        }
        if (lastIndex < text.length()) {
            // 将最后一个匹配后的文本添加到结果中
            String nonMatchText = text.substring(lastIndex);
            result.add(nonMatchText);
        }
        ArrayList<String> list = new ArrayList<>();
        for (String arr : result) {
            if (!arr.contains("\"")) {
                list.addAll(Arrays.asList(arr.split(" ")));
            } else {
                String substring = arr.substring(1, arr.length() - 1);
                list.add(substring);
            }
        }
        return list.stream().filter(sub -> !sub.isEmpty()).collect(Collectors.toList());
    }

    public static String[] listToStringArray(List<String> list) {
        String[] arr = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            arr[i] = list.get(i);
        }
        return arr;
    }
}
