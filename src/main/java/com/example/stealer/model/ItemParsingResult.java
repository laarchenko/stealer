package com.example.stealer.model;

import com.example.stealer.enums.SizeType;
import com.example.stealer.exception.ApplicationException;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.Map;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class ItemParsingResult {

    Item item;

    ApplicationException exception;
}
