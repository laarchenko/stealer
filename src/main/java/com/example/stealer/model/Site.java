package com.example.stealer.model;

import com.example.stealer.enums.SiteName;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Site {

    Long id;

    Boolean enabled;

    SiteName siteName;

    String url;
}
