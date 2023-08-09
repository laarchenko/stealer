package com.example.stealer;

import com.example.stealer.core.Parser;
import com.example.stealer.core.ParsingTask;
import com.example.stealer.mapper.ItemMapper;
import com.example.stealer.service.ItemService;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Component
public class ParsingRunner {

    private final WebDriver driver;
    private final List<Parser> parsers;
    private final ScheduledExecutorService executor;
    private final ItemService itemService;
    private final ItemMapper itemMapper;

    ParsingRunner(
            @Value("${scheduling.initial-delay}") Integer initialDelay,
            @Value("${scheduling.period}") Integer period,
            @Value("${scheduling.time-unit}") String timeUnit,
            WebDriver driver, List<Parser> parsers, ScheduledExecutorService executor,
            ItemService itemService, ItemMapper itemMapper) {

        this.itemService = itemService;
        this.driver = driver;
        this.parsers = parsers;
        this.executor = executor;
        this.itemMapper = itemMapper;

        executor.scheduleAtFixedRate(new ParsingTask(driver, parsers, itemService, itemMapper),
                initialDelay, period, TimeUnit.valueOf(timeUnit));
    }

}
