package com.example.stealer.service;

import com.example.stealer.model.Item;

import java.util.List;

public interface ItemService {

    Item create(Item item);

    Item update(Item item);

    List<Item> findAll();
}
