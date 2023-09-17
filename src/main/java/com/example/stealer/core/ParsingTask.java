package com.example.stealer.core;

import com.example.stealer.service.ItemService;
import com.example.stealer.service.ParsingService;
import lombok.AccessLevel;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PROTECTED)
public class ParsingTask implements Runnable {

    private final ItemService itemService;
    private final ParsingService parsingService;

    @Override
    public void run() {

        var itemParsingRequests = itemService.getItemParsingRequests();

        var itemParsingResults = parsingService.executeParsing(itemParsingRequests);

        itemService.processParsingResults(itemParsingResults);
    }
}
