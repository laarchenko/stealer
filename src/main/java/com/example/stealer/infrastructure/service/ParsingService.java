package com.example.stealer.infrastructure.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.RunnableFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;

@Service
public class ParsingService {

    ScheduledThreadPoolExecutor executor;

    ParsingService() {
        executor = new ScheduledThreadPoolExecutor();
        var future = executor.schedule(new RunnableFuture<>() {});
        future
    }


}
