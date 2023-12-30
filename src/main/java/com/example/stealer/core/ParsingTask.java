package com.example.stealer.core;

import com.example.stealer.service.ItemService;
import com.example.stealer.service.ParsingService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PROTECTED)
public class ParsingTask implements Runnable {

    private final ItemService itemService;
    private final ParsingService parsingService;

    @Override
    public void run() {

        try {
            var itemParsingRequests = itemService.getItemParsingRequests();

            var itemParsingResults = parsingService.executeParsing(itemParsingRequests);

            itemService.processParsingResults(itemParsingResults);
        } catch (Exception e) {
            log.error("Exception occurred in parsing task", e);
        }
    }
}
