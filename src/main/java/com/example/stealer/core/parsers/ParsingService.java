package com.example.stealer.core.parsers;

import com.example.stealer.core.parsers.impl.DollskillParser;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ParsingService {

    public static void main(String[] args) {

        System.setProperty("webdriver.chrome.driver", "/home/vampir/soft/chromedriver");//get from config
        var options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        var driver = new ChromeDriver(options);
        driver.get("https://www.dollskill.com/products/obsidian-pocket-combat-boots");


        DollskillParser dollskillParser = new DollskillParser();
        dollskillParser.setDriver(driver);

        System.out.println(dollskillParser.execute());
    }
}
