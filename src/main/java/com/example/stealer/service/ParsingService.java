package com.example.stealer.service;

import com.example.stealer.model.ItemParsingRequest;
import com.example.stealer.model.ItemParsingResult;

import java.util.List;

public interface ParsingService {

    List<ItemParsingResult> executeParsing(List<ItemParsingRequest> inputItems);
}
