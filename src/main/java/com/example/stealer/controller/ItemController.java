package com.example.stealer.controller;

import com.example.stealer.entity.Item;
import com.example.stealer.parsing.Parser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemController {

    @Autowired
    List<Parser> parsers;

    @GetMapping("")
    public List getAllItems() {
        return parsers;
    }
}
