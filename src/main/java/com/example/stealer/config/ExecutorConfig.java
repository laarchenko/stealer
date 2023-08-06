package com.example.stealer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Component
public class ExecutorConfig {

    @Bean
    public Executor getExecutor() {
        return Executors.newCachedThreadPool();
    }
}
