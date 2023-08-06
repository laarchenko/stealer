package com.example.stealer.service;

import com.example.stealer.config.WebDriverConfig;
import com.example.stealer.core.parsers.Parser;
import com.example.stealer.core.parsers.ParsingTask;
import com.example.stealer.core.parsers.impl.DollskillParser;
import com.example.stealer.enums.SiteName;
import com.example.stealer.exception.NoParserForSiteException;
import com.example.stealer.model.ParsingInput;
import com.example.stealer.model.Site;
import lombok.RequiredArgsConstructor;
import org.hibernate.query.sql.internal.ParameterRecognizerImpl;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;

@Service
public class ParsingService {

    private final WebDriver driver;
    private final List<Parser> parsers;

    private final Executor executor;

    ParsingService(WebDriver driver, List<Parser> parsers, Executor executor) {

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

        executor.execute(new ParsingTask(parsingInputList, driver, parsers));

    }

}
