package com.example.stealer.model;

import com.example.stealer.enums.SiteName;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ItemParsingRequest {

    Long id;

    String url;

    SiteName siteName;
}
