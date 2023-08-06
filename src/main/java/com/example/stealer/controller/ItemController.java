package com.example.stealer.controller;

import com.example.stealer.core.parsers.ParsingTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemController {

    @Autowired
    List<ParsingTask> parsers;

    @GetMapping("")
    public List getAllItems() {
        return parsers;
    }
}
