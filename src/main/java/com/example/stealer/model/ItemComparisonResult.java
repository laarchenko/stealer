package com.example.stealer.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ItemComparisonResult {

    List<User> users;

    List<ItemDetails> newSizes;

    List<ItemDetails> newPrices;
}
