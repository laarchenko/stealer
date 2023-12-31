package com.example.stealer.model;

import lombok.Builder;
import lombok.Data;

import java.util.Locale;

@Data
@Builder
public class User {

    Long id;

    String name;

    String lastname;

    Locale locale;

    String telegramUsername;

    Long telegramId;
}
