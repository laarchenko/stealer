package com.example.stealer.utils;

import com.example.stealer.exception.ApplicationException;
import lombok.experimental.UtilityClass;
import org.openqa.selenium.WebElement;

import java.util.Arrays;
import java.util.Objects;

@UtilityClass
public class WebElementUtils {

    public static WebElement resolveNonNull(ApplicationException e, WebElement... elements) {
        return Arrays.stream(elements)
                .filter(Objects::nonNull)
                .findFirst()
                .orElseThrow(() -> e);
    }
}
