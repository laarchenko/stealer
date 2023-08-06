package com.example.stealer.config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class WebDriverConfig {

    @Bean
    public WebDriver getWebDriver() {
        System.setProperty("webdriver.chrome.driver", "/home/vampir/soft/chromedriver");
        var options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        return new ChromeDriver(options);
    }
}
