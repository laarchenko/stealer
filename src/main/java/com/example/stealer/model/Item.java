package com.example.stealer.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;


@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Item {

    Long id;

    String url;

    List<ItemDetails> itemDetails;

    String name;

    String pictureUrl;

    Site site;
}
