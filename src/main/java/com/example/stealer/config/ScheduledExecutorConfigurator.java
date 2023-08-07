package com.example.stealer.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

@Component
public class ScheduledExecutorConfigurator {

    @Value("${scheduling.core-pool-size}")
    Integer corePoolSize;

    @Bean
    public ScheduledExecutorService getExecutor() {
        return Executors.newScheduledThreadPool(corePoolSize);
    }
}
