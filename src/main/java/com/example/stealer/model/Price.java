package com.example.stealer.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Price {

    Long id;

    BigDecimal price;

    Long size;

    Instant timestamp;//TODO Add currency
}
