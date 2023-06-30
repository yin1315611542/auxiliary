package cn.goldencis.auxiliary.infrastructure.common;

import org.apache.commons.exec.*;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @program: auxiliary
 * @description:
 * @Author: yinhd
 * @create: 2023-06-21 13:59
 **/
public class CommandUtil {
    public static String execute(String path,String command) {
        try {
            CommandLine cmdLine = CommandLine.parse(command);
            Executor executor = new DefaultExecutor();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            PumpStreamHandler streamHandler = new PumpStreamHandler(outputStream);
            executor.setStreamHandler(streamHandler);
            int exitValue = executor.execute(cmdLine);
            String output = outputStream.toString();
//            System.out.println("Exit value: " + exitValue);
//            System.out.println("Output: " + output);
            return output;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static String execute2(String path,String command){
        try {
            Process process = Runtime.getRuntime().exec("");
            int i = process.waitFor();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            reader.close();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
