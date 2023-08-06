package com.example.stealer.model;

import com.example.stealer.model.ItemParsingResult;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SiteParsingResult {

    List<ItemParsingResult> itemParsingResultList;
}
