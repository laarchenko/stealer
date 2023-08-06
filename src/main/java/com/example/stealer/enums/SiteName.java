package com.example.stealer.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public enum SiteName {

    DOLLSKILL("Dollskill");

    private final String value;

    SiteName(String value) {
        this.value = value;
    }
}
