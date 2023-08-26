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

    List<Price> prices;

    String name;

    String pictureUrl;

    Size size;

    Site site;
}
