package com.example.stealer.service.impl;

import com.example.stealer.mapper.ItemEntityMapper;
import com.example.stealer.model.Item;
import com.example.stealer.repo.ItemRepo;
import com.example.stealer.service.ItemService;
import com.example.stealer.service.SiteService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final SiteService siteService;
    private final ItemRepo itemRepo;
    private final ItemEntityMapper itemEntityMapper;

    @Override
    @Transactional
    public Item create(Item item) {

        var site = siteService.resolveSiteByUrl(item.getUrl());
        item.setSite(site);//TODO implement
        var toSaveEntity = itemEntityMapper.toEntity(item);
        var savedEntity = itemRepo.save(toSaveEntity);
        return itemEntityMapper.toModel(savedEntity);
    }

    @Override
    @Transactional
    public Item update(Item item) {
        var entity = itemRepo.findById(item.getId()).orElseThrow();//TODO add exception
        var savedEntity = itemRepo.save(entity);
        return itemEntityMapper.toModel(savedEntity);
    }

    @Override
    public List<Item> findAll() {
        return itemRepo.findAll().stream().map(itemEntityMapper::toModel).collect(Collectors.toList());
    }
}
