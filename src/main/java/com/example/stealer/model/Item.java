package com.example.stealer.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;


@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Item {

    Long id;

    String url;

    Price price;

    String name;

    String pictureUrl;

    Size size;

    Site site;
}
