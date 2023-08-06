package com.example.stealer.core.parsers.impl;

import com.example.stealer.entity.ItemEntity;
import com.example.stealer.enums.Currency;
import com.example.stealer.enums.SizeType;
import com.example.stealer.exception.ItemNameNotFoundException;
import com.example.stealer.exception.ItemPriceNotFoundException;
import com.example.stealer.model.Item;
import com.example.stealer.model.ItemParsingResult;
import com.example.stealer.model.Price;
import com.example.stealer.model.Size;
import lombok.Data;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static com.example.stealer.utils.StringUtils.removeSymbol;
import static com.example.stealer.utils.WebElementUtils.resolveNonNull;

@Component
@Data
public class DollskillParser {

    @Value("$chrome-driver")
    private String chromeDriver;

   private final String mainUrl = "https://www.dollskill.com/products/obsidian-pocket-combat-boots";

    public WebDriver driver;//set driver outside

    private String getName() {
        var nameElementByStrictXpath = findElementBy(By.xpath("/html/body/div[7]/main/div/div[1]/div[2]/div/h1"));//full
        var nameElementByRelativeXpath = findElementBy(By.xpath("//*[@id=\"product-content\"]/div/h1"));
        var nameElementByCssSelector = findElementBy(By.cssSelector("#product-content > div > h1"));

        return resolveNonNull(
                new ItemNameNotFoundException(),
                nameElementByStrictXpath,
                nameElementByRelativeXpath,
                nameElementByCssSelector).getText();
    }

    private BigDecimal getPriceValue() {
        //List<WebElement> elements = driver.findElements(By.className("product-price"));
        var priceElementByCssSelector = findElementBy(By.cssSelector("#product-content > div > p.product-price"));
        var priceElementByRelativeXpath = findElementBy(By.xpath("//*[@id=\"product-content\"]/div/p[1]"));
        var priceElementByStrictXpath = findElementBy(By.xpath("/html/body/div[7]/main/div/div[1]/div[2]/div/p[1]"));//Price is doubled when it is on sale

        var priceAsString = resolveNonNull(
                new ItemPriceNotFoundException(),
                priceElementByStrictXpath,
                priceElementByRelativeXpath,
                priceElementByCssSelector).getText();

        return BigDecimal.valueOf(Double.parseDouble( removeSymbol((extractPriceValue(priceAsString)), "$")));
    }

    private Size getSize() {
        WebElement sizes = driver.findElement(By.id("cart-options"));
        var sizeList = sizes.findElements(By.className("shorthand"));
        return Size.of(resolveSizeType(sizeList), resolveSizeValues(sizeList));
    }

    private static List<Integer> resolveSizeValues(List<WebElement> sizeList) {
        return sizeList.stream()
                .filter(element -> !element.getAttribute("data-quantity").equals("0"))
                .map(DollskillParser::resolveSizeValue).collect(Collectors.toList());
    }

    private SizeType resolveSizeType(List<WebElement> sizeList) {
        if (sizeList != null && !sizeList.isEmpty()) {
           return SizeType.valueOf(sizeList.get(0).getText().split(" ")[0]);
        }
        return null;
    }

    private String getPictureUrl() {
        WebElement picture = driver.findElement(By.cssSelector("#image-container > div:nth-child(1) > a"));
        System.out.println("PIC "+picture.getAttribute("href"));
        return "PIC "+picture.getAttribute("href");//TODO maybe parse few pictures
    }

    public ItemParsingResult execute() {
        var result= ItemParsingResult.builder()
                .item(Item.builder()
                        .name(getName())
                        .price(Price.builder()
                                .value(getPriceValue())
                                .currency(Currency.USD)
                                .build())
                        .pictureUrl(getPictureUrl())
                        .size(getSize())
                        .build())
                .build();
        driver.quit();
        return result;
    }

    private static Integer resolveSizeValue(WebElement element) {
        var rawSize = element.getText().split(" ");
        return Integer.valueOf(rawSize[1]);
    }

    private WebElement findElementBy(By locator) {
        try {
            return driver.findElement(locator);
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    private static String extractPriceValue(String input) {
        // Regex pattern to match a price in the format $123.45
        Pattern pattern = Pattern.compile("\\$\\d+(\\.\\d{2})?");
        Matcher matcher = pattern.matcher(input);

        String firstPrice = null;
        String secondPrice = null;

        // Check if matches are found and store them in firstPrice and secondPrice variables
        while (matcher.find()) {
            if (firstPrice == null) {
                firstPrice = matcher.group();
            } else if (secondPrice == null) {
                secondPrice = matcher.group();
            }
        }

        // Return the second price if it exists, otherwise return the first price
        return (secondPrice != null) ? secondPrice : firstPrice;
    }
}
