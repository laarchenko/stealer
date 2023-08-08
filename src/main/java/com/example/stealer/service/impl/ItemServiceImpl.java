package com.example.stealer.service.impl;

import com.example.stealer.mapper.ItemEntityMapper;
import com.example.stealer.model.Item;
import com.example.stealer.repo.ItemRepo;
import com.example.stealer.service.ItemService;
import com.example.stealer.service.SiteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final SiteService siteService;
    private final ItemRepo itemRepo;
    private final ItemEntityMapper itemEntityMapper;

    @Override
    public Item create(Item item) {

        var site = siteService.resolveSiteByUrl(item.getUrl());
        item.setSite(site);//TODO implement
        var toSaveEntity = itemEntityMapper.toEntity(item);
        var savedEntity = itemRepo.save(toSaveEntity);
        return itemEntityMapper.toModel(savedEntity);
    }

    @Override
    public Item update(Item item) {
        return null;
    }
}
