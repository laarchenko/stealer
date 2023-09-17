package com.example.stealer.model;

import com.example.stealer.exception.ApplicationException;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class ItemParsingResult {

    Long itemId;

    List<ItemDetails> itemDetails;

    String name;

    String pictureUrl;

    ApplicationException exception;
}
