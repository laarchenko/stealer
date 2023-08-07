package com.example.stealer.service;

import com.example.stealer.core.Parser;
import com.example.stealer.core.ParsingTask;
import com.example.stealer.enums.SiteName;
import com.example.stealer.model.ParsingInput;
import com.example.stealer.model.Site;
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

    ParsingRunner(
            @Value("${scheduling.initial-delay}") Integer initialDelay,
            @Value("${scheduling.period}") Integer period,
            @Value("${scheduling.time-unit}") String timeUnit,
            WebDriver driver, List<Parser> parsers, ScheduledExecutorService executor) {

        this.driver = driver;
        this.parsers = parsers;
        this.executor = executor;

        var parsingInputList = List.of(ParsingInput.builder()
                .url("https://www.dollskill.com/products/obsidian-pocket-combat-boots")
                .site(Site.builder()
                        .siteName(SiteName.DOLLSKILL)
                        .enabled(true)
                        .build())
                .build());

        executor.scheduleAtFixedRate(new ParsingTask(parsingInputList, driver, parsers),
                initialDelay, period, TimeUnit.valueOf(timeUnit));
    }

}
