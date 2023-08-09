package com.example.stealer.model;

import com.example.stealer.exception.ApplicationException;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class ItemParsingResult {

    Price price;

    String name;

    String pictureUrl;

    Size size;

    ApplicationException exception;
}
