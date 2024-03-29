package com.example.stealer.service.impl;

import com.example.stealer.entity.ItemEntity;
import com.example.stealer.entity.SubscriptionEntity;
import com.example.stealer.enums.SubscriptionType;
import com.example.stealer.exception.InvalidUrlException;
import com.example.stealer.exception.ItemNotFoundException;
import com.example.stealer.mapper.ItemParsingMapper;
import com.example.stealer.mapper.entity.ItemDetailsEntityMapper;
import com.example.stealer.mapper.entity.ItemEntityMapper;
import com.example.stealer.mapper.entity.SiteEntityMapper;
import com.example.stealer.mapper.entity.UserEntityMapper;
import com.example.stealer.model.*;
import com.example.stealer.repo.ItemRepo;
import com.example.stealer.repo.SiteRepo;
import com.example.stealer.service.ItemService;
import com.example.stealer.service.NotificationService;
import com.example.stealer.service.SiteService;
import com.example.stealer.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final SiteService siteService;
    private final ItemRepo itemRepo;
    private final ItemEntityMapper itemEntityMapper;
    private final ItemDetailsEntityMapper itemDetailsEntityMapper;
    private final ItemParsingMapper itemParsingMapper;
    private final UserEntityMapper userEntityMapper;
    private final SiteEntityMapper siteEntityMapper;
    private final NotificationService notificationService;
    private final UserService userService;

    private final SiteRepo siteRepo;

    @Override
    @Transactional
    public void createOrUpdateByUrl(String itemUrl, Long userId) {

        if(!isValidUrl(itemUrl)) {
            throw new InvalidUrlException(itemUrl);
        }

        itemUrl = shortUrl(itemUrl);
        
        var itemFromDb = itemRepo.findByUrl(itemUrl);
        if(itemFromDb.isPresent()) {
            update(itemFromDb.get(), userId);
        } else {
            create(itemUrl, userId);
        }
    }

    public Stream<Item> findBySite(Site site) {
        return siteRepo.findById(site.getId()).orElseThrow()
                .getItems().stream()
                .map(itemEntity -> {
                    var item = itemEntityMapper.toModel(itemEntity);
                    item.setUrl(site.getUrl() + item.getUrl());
                    return item;
                });
    }

    private String shortUrl(String itemUrl) {
        if (itemUrl.endsWith("/")) {
            itemUrl = itemUrl.substring(0, itemUrl.length() - 1);
        }
        return itemUrl;
    }

    private boolean isValidUrl(String itemUrl) {
        try {
            new URL(itemUrl);
            return true;
        } catch (MalformedURLException e) {
            return false;
        }
    }

    private void update(ItemEntity entity, Long userId) {
        addUser(entity, userId);
        itemRepo.save(entity);
    }

    @Transactional
    private void create(String itemUrl, Long userId) {

        var siteModel = siteService.getSiteByItemUrl(itemUrl);
        var siteEntity = siteEntityMapper.toEntity(siteModel);
        var itemModel = Item.builder()
                .url(itemUrl)
                .site(siteModel)
                .itemDetails(Collections.emptyList())
                .subscriptions(Collections.emptyList())//TODO Add user
                //.pictureUrl() TODO resolvePictureUrl
                .name("")//TODO resolve name
                .build();

        var toSaveItemEntity = itemEntityMapper.toEntity(itemModel);
        toSaveItemEntity.setSite(siteEntity);
        addUser(toSaveItemEntity, userId);
        itemRepo.save(toSaveItemEntity);
    }

    @Transactional
    private void addUser(ItemEntity toSaveItemEntity, Long userId) {

        var userEntity = userEntityMapper.toEntity(userService.findById(userId));
        var subscriptions = toSaveItemEntity.getSubscriptions();
        subscriptions.add(SubscriptionEntity.builder()
                        .item(toSaveItemEntity)
                        .type(SubscriptionType.PRICEDROP)
                        .users(List.of())//TODO Add user
                .build());
        subscriptions.add(SubscriptionEntity.builder()
                .item(toSaveItemEntity)
                .type(SubscriptionType.SIZE_AVAILABLE)
                .users(List.of())//TODO Add user
                .build());
    }

    @Transactional
    private void processParsingResult(ItemParsingResult newItem) {
        var initialItemEntity = itemRepo.findById(newItem.getItemId())
                .orElseThrow(() -> new ItemNotFoundException(newItem.getItemId()));//TODO do i really need exception ? or just log
        var initialItem = itemEntityMapper.toModel(initialItemEntity);
        itemEntityMapper.updateFromItemParsingResult(initialItemEntity, newItem);
        updateItemDetails(initialItemEntity, newItem.getItemDetails());
        itemRepo.save(initialItemEntity);
        compareAndNotify(newItem, initialItem);
    }

    private void compareAndNotify(ItemParsingResult newItem, Item initialItem) {

        var newSizes = checkOnNewSizes(newItem.getItemDetails(), initialItem.getItemDetails());
        var newPrices = checkOnNewPrices(newItem.getItemDetails(), initialItem.getItemDetails());

        initialItem.getSubscriptions().forEach(
                subscription -> {
                    switch (subscription.getType()) {
                        case PRICEDROP -> notificationService.processItemComparisonResult(ItemComparisonResult.builder().users(subscription.getUsers()).newPrices(newPrices).build(), SubscriptionType.PRICEDROP);
                        case SIZE_AVAILABLE -> notificationService.processItemComparisonResult(ItemComparisonResult.builder().users(subscription.getUsers()).newSizes(newSizes).build(), SubscriptionType.SIZE_AVAILABLE);
                        default -> throw new RuntimeException();//TODO Add exception
                    }
                }
        );
    }

    private List<ItemDetails> checkOnNewPrices(List<ItemDetails> newItemDetails, List<ItemDetails> initialItemDetails) {
        return newItemDetails.stream().filter(newItem -> {
            var item = initialItemDetails.stream().filter(initialItem -> initialItem.getSizeType() == initialItem.getSizeType() && initialItem.getSize().equals(newItem.getSize())).findFirst().orElse(null);

            return item != null && !item.getPrice().equals(newItem.getPrice());
        }).collect(Collectors.toList());
    }

    private List<ItemDetails> checkOnNewSizes(List<ItemDetails> newItemDetails, List<ItemDetails> initialItemDetails) {
        Iterator<ItemDetails> iterator = newItemDetails.iterator();
        while (iterator.hasNext()) {
            ItemDetails newItem = iterator.next();
            for (ItemDetails initialItem : initialItemDetails) {
                if (newItem.getSize().equals(initialItem.getSize()) && newItem.getSizeType() == initialItem.getSizeType()) {
                    iterator.remove();
                    break;
                }
            }
        }
        return newItemDetails;
    }

    private void updateItemDetails(ItemEntity entity, List<ItemDetails> models) {
        var entities = entity.getItemDetails();
        entities.addAll(models.stream().map(itemDetailModel -> {
            var mappedItemDetailEntity = itemDetailsEntityMapper.toEntity(itemDetailModel);
            mappedItemDetailEntity.setItem(entity);
            return mappedItemDetailEntity;
        }).toList());
    }


    @Override
    @Transactional
    public void processParsingResults(List<ItemParsingResult> items) {
        items.forEach(this::processParsingResult);
    }

    @Override
    @Transactional
    public List<ItemParsingRequest> getItemParsingRequests() {

        var itemParsingRequests = new ArrayList<ItemParsingRequest>();

        var sites = siteService.getAllEnabled();
        sites.forEach(site -> {
            var items = findBySite(site);
            itemParsingRequests.addAll(items.map(itemParsingMapper::toRequest).toList());
        });
        return itemParsingRequests;
    }
}
