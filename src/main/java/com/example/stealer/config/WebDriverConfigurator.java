package com.example.stealer.config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class WebDriverConfigurator {

    @Value("${web-driver.type}")
    String type;

    @Value("${web-driver.location}")
    String location;

    @Value("${web-driver.args}")
    String[] args;

    @Bean
    public WebDriver getWebDriver() {
        System.setProperty(type, location);
        var options = new ChromeOptions();
        options.addArguments(args);
        return new ChromeDriver(options);
    }
}
