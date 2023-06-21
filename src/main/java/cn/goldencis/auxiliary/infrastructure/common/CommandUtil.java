package cn.goldencis.auxiliary.infrastructure.common;

import org.apache.commons.exec.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @program: auxiliary
 * @description:
 * @Author: yinhd
 * @create: 2023-06-21 13:59
 **/
public class CommandUtil {
    public static String execute(String path,String cmd) {
        try {
            String logFile = path;
            String command = "cat " + logFile ;
            CommandLine cmdLine = CommandLine.parse(command);
            DefaultExecutor executor = new DefaultExecutor();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            PumpStreamHandler streamHandler = new PumpStreamHandler(outputStream);
            executor.setStreamHandler(streamHandler);
            int exitValue = executor.execute(cmdLine);
            String output = outputStream.toString();
            System.out.println("Exit value: " + exitValue);
            System.out.println("Output: " + output);
            return output;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
