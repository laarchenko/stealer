package com.example.stealer.core.parsers;

import com.example.stealer.exception.ApplicationException;
import com.example.stealer.model.Item;
import com.example.stealer.model.ItemParsingResult;
import com.example.stealer.model.Price;
import com.example.stealer.model.Size;
import lombok.RequiredArgsConstructor;
import org.openqa.selenium.WebDriver;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public abstract class Parser {

    protected final WebDriver driver;

    protected final List<String> itemUrls;

    public List<ItemParsingResult> execute() {
        return itemUrls.stream()
                .map(this::geItemParsingResult)
                .collect(Collectors.toList());
    }

    protected ItemParsingResult geItemParsingResult(String itemUrl) {
        try {
            return ItemParsingResult.builder()
                    .item(resolveItem(itemUrl))
                    .build();
        } catch (ApplicationException e) {
            return ItemParsingResult.builder()
                    .exception(e)
                    .build();
        }
    }

    protected Item resolveItem(String itemUrl) {
        driver.get(itemUrl);
        return Item.builder()
                .name(getName())
                .price(getPriceValue())
                .pictureUrl(getPictureUrl())
                .size(getSize())
                .build();
    }

    protected abstract String getName();

    protected abstract Price getPriceValue();

    protected abstract String getPictureUrl();

    protected abstract Size getSize();
}
