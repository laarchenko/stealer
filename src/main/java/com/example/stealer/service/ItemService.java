package com.example.stealer.service;

import com.example.stealer.model.ItemParsingRequest;
import com.example.stealer.model.ItemParsingResult;

import java.util.List;

public interface ItemService {

    void createOrUpdateByUrl(String itemUrl, Long userId);

    void processParsingResults(List<ItemParsingResult> itemParsingResults);

    List<ItemParsingRequest> getItemParsingRequests();
}
