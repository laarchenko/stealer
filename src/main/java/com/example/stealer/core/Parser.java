package com.example.stealer.core;

import com.example.stealer.enums.SiteName;
import com.example.stealer.exception.ApplicationException;
import com.example.stealer.model.ItemDetails;
import com.example.stealer.model.ItemParsingResult;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.openqa.selenium.WebDriver;
import org.springframework.stereotype.Component;

import java.util.List;

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
                    .itemDetails(getItemDetails())
                    .pictureUrl(getPictureUrl())
                    .build();
        } catch (ApplicationException e) {
            return ItemParsingResult.builder()
                    .exception(e)
                    .build();
        }
    }

    protected abstract String getName();

    protected abstract List<ItemDetails> getItemDetails();

    protected abstract String getPictureUrl();
}
