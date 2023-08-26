package com.example.stealer.core;

import com.example.stealer.enums.SiteName;
import com.example.stealer.exception.NoParserForSiteException;
import com.example.stealer.mapper.ItemMapper;
import com.example.stealer.model.Item;
import com.example.stealer.service.ItemService;
import lombok.AccessLevel;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebDriver;

import java.util.List;

@Data
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PROTECTED)
public class ParsingTask implements Runnable {

    private final WebDriver driver;
    private final List<Parser> parsers;
    private final ItemService itemService;
    private final ItemMapper itemMapper;

    @Override
    public void run() {

        var inputItems = getItemsToParse();

        //var results = parseItems(inputItems);
        //TODO extract everything about parsing to parsing service
        //TODO rename ParsingTask
        //results.forEach(System.out::println);
        inputItems.forEach(System.out::println);
        //results.stream().map(ItemParsingResult::getItem).forEach(itemService::update);
        //driver.quit(); //TODO check quitting driver
    }

    @NotNull
    private List<Item> parseItems(List<Item> inputItems) {
        return inputItems.stream()
                .filter(input -> input.getSite().getEnabled())
                .peek(input -> {
                    var parserForInput = getParserBySiteName(parsers, input.getSite().getValue());
                    var result = parserForInput.execute(input.getUrl());
                    itemMapper.updateModel(input, result);
                }).toList();
    }

    private List<Item> getItemsToParse() {
        return itemService.findAll();
    }

    private static Parser getParserBySiteName(List<Parser> parsers, SiteName siteName) {
        return parsers.stream()
                .filter(parser -> parser.getSiteName().equals(siteName))
                .findFirst()
                .orElseThrow(() -> new NoParserForSiteException(siteName));
    }
}
