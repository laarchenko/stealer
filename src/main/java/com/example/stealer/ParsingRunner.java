package com.example.stealer;

import com.example.stealer.core.ParsingTask;
import com.example.stealer.service.ItemService;
import com.example.stealer.service.ParsingService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Component
public class ParsingRunner {

    ParsingRunner(
            @Value("${scheduling.initial-delay}") Integer initialDelay,
            @Value("${scheduling.period}") Integer period,
            @Value("${scheduling.time-unit}") String timeUnit,
            ScheduledExecutorService executor,
            ItemService itemService, ParsingService parsingService) {

        executor.scheduleAtFixedRate(new ParsingTask(itemService, parsingService),
                initialDelay, period, TimeUnit.valueOf(timeUnit));
    }

}
