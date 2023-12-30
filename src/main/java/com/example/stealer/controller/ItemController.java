package com.example.stealer.controller;

import com.example.stealer.dto.request.CreateItemRequest;
import com.example.stealer.service.ItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/item")
@RequiredArgsConstructor
public class ItemController {


    private final ItemService itemService;

    @PostMapping(
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public void create(@RequestBody @Valid CreateItemRequest request) {
        itemService.createOrUpdateByUrl(request.getUrl(), request.getUserId());
    }
}
