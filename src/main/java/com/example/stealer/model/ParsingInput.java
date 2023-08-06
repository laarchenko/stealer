package com.example.stealer.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ParsingInput {

    String url;

    Site site;
}
