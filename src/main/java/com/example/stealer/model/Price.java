package com.example.stealer.model;

import com.example.stealer.enums.Currency;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Price {

    BigDecimal value;

    Currency currency;
}
