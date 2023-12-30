package com.example.stealer.model;

import com.example.stealer.enums.SiteName;
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

    SiteName siteName;

    String pictureUrl;

    Site site;

    List<Subscription> subscriptions;
}
