package com.example.stealer.model;

import com.example.stealer.enums.Currency;
import com.example.stealer.enums.SizeType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ItemDetails {

    Long id;

    BigDecimal price;

    Currency currency;

    Long size;

    SizeType sizeType;

    Instant timestamp;
}
