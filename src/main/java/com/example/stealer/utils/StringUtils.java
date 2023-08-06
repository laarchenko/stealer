package com.example.stealer.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class StringUtils {

    public static String removeSymbol(String input, String symbol) {
        return input.replace(symbol, "");
    }
}
