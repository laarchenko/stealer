package com.example.stealer.controller;

import com.example.stealer.config.WebDriverConfigurator;
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
    WebDriverConfigurator webDriverConfigurator;

    @GetMapping("/")
    @ResponseBody
    public List getAllItems() {
        System.out.println(executor);
        System.out.println(webDriverConfigurator);
        return List.of();
    }
}
