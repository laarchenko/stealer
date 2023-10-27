package com.example.stealer.service.impl;

import com.example.stealer.core.Parser;
import com.example.stealer.enums.SiteName;
import com.example.stealer.exception.NoParserForSiteException;
import com.example.stealer.model.ItemParsingRequest;
import com.example.stealer.model.ItemParsingResult;
import com.example.stealer.service.ParsingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ParsingServiceImpl implements ParsingService {

    private final List<Parser> parsers;

    public List<ItemParsingResult> executeParsing(List<ItemParsingRequest> itemParsingRequests) {
        return itemParsingRequests.stream()
                .map(input -> {
                    var parserForInput = getParserBySiteName(parsers, input.getSiteName());
                    var result = parserForInput.execute(input.getUrl());

                    if (result.getException() != null) {
                        //TODO do something
                    }
                    result.setItemId(input.getId());
                    return result;
                }).toList();
    }

    private static Parser getParserBySiteName(List<Parser> parsers, SiteName siteName) {
        return parsers.stream()
                .filter(parser -> parser.getSiteName().equals(siteName))
                .findFirst()
                .orElseThrow(() -> new NoParserForSiteException(siteName));
    }
}
