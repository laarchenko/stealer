package com.example.stealer.controller;

import com.example.stealer.config.WebDriverConfig;
import com.example.stealer.core.parsers.ParsingTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.Executor;

@RestController
@RequestMapping("/items")
public class ItemController {

    @Autowired
    Executor executor;

    @Autowired
    WebDriverConfig webDriverConfig;

    @GetMapping("/")
    @ResponseBody
    public List getAllItems() {
        System.out.println(executor);
        System.out.println(webDriverConfig);
        return List.of();
    }
}
