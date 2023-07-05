package cn.goldencis.auxiliary.domain.deploy.config;

import cn.goldencis.auxiliary.domain.deploy.Hub;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class HubConfig {
    @Bean
    public List<Hub> hubs() {
        return new ArrayList<>();
    }
}
