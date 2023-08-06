package com.example.stealer.model;

import com.example.stealer.enums.SizeType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;


@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Item {

    String url;

    Price price;

    String name;

    String pictureUrl;

    Size size;

    Site site;
}
