package com.example.stealer.core.parsers;

import com.example.stealer.entity.ItemEntity;
import com.example.stealer.enums.SiteName;
import com.example.stealer.exception.NoParserForSiteException;
import com.example.stealer.model.*;
import com.example.stealer.entity.SiteEntity;
import lombok.AccessLevel;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.openqa.selenium.WebDriver;

import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

@Data
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PROTECTED)
public class ParsingTask implements Runnable {

    private final List<ParsingInput> parsingInputList;

    private final WebDriver driver;
    private final List<Parser> parsers;

    @Override
    public void run() {


        var results = parsingInputList.stream()
                .filter(input -> input.getSite().getEnabled())
                .map(input -> {
                    var parserForInput = getParserBySiteName(parsers, input.getSite().getSiteName());
                    return parserForInput.execute(input.getUrl());
                }).toList();
        results.forEach(System.out::println);

        //driver.quit();
    }

    private static Parser getParserBySiteName(List<Parser> parsers, SiteName siteName) {
        return parsers.stream()
                .filter(parser -> parser.getSiteName().equals(siteName))
                .findFirst()
                .orElseThrow(() -> new NoParserForSiteException(siteName));
    }
}
