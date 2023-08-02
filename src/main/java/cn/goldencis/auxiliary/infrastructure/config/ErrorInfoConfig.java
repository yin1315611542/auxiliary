package cn.goldencis.auxiliary.infrastructure.config;

import cn.goldencis.auxiliary.domain.loginfo.ErrorInfo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * @program: auxiliary
 * @description:
 * @Author: yinhd
 * @create: 2023-06-25 15:32
 **/
@Configuration
public class ErrorInfoConfig {

    @Bean
    public LinkedBlockingQueue<ErrorInfo> errorInfos(){
        return new LinkedBlockingQueue<>();
    }
}
