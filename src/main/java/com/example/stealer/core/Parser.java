package com.example.stealer.core;

import com.example.stealer.enums.SiteName;
import com.example.stealer.exception.ApplicationException;
import com.example.stealer.model.ItemParsingResult;
import com.example.stealer.model.Price;
import com.example.stealer.model.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.openqa.selenium.WebDriver;
import org.springframework.stereotype.Component;

@Getter
@Component
@RequiredArgsConstructor
public abstract class Parser {

    protected final SiteName siteName;

    protected final WebDriver driver;

    public ItemParsingResult execute(String url) {
        return geItemParsingResult(url);
    }

    protected ItemParsingResult geItemParsingResult(String itemUrl) {
        try {
            driver.get(itemUrl);
            return ItemParsingResult.builder()
                    .name(getName())
                    .price(getPrice())
                    .pictureUrl(getPictureUrl())
                    .size(getSize())
                    .build();
        } catch (ApplicationException e) {
            return ItemParsingResult.builder()
                    .exception(e)
                    .build();
        }
    }

    protected abstract String getName();

    protected abstract Price getPrice();

    protected abstract String getPictureUrl();

    protected abstract Size getSize();
}
