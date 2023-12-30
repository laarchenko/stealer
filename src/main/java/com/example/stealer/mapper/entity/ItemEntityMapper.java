package com.example.stealer.mapper.entity;

import com.example.stealer.entity.ItemDetailsEntity;
import com.example.stealer.entity.ItemEntity;
import com.example.stealer.entity.SiteEntity;
import com.example.stealer.entity.SubscriptionEntity;
import com.example.stealer.model.*;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(uses = {UserEntityMapper.class, SubscriptionEntityMapper.class})
public abstract class ItemEntityMapper {

    @Autowired
    ItemDetailsEntityMapper itemDetailsEntityMapper;

    @Autowired
    SiteEntityMapper siteEntityMapper;

    @Autowired
    SubscriptionEntityMapper subscriptionEntityMapper;

    @Autowired
    UserEntityMapper userEntityMapper;

    @Mapping(target = "itemDetails", source = "itemDetails", qualifiedByName = "mapItemDetailsToEntities")
    @Mapping(target = "site", source = "site", qualifiedByName = "mapSiteToEntity")
    public abstract ItemEntity toEntity(Item item);

    @Mapping(target = "itemDetails", ignore = true)
    public abstract void updateFromItemParsingResult(@MappingTarget ItemEntity entity, ItemParsingResult model);

    @Mapping(target = "itemDetails", source = "itemDetails", qualifiedByName = "mapItemDetails")
    @Mapping(target = "site", source = "site", qualifiedByName = "mapSite")
    @Mapping(target = "subscriptions", source = "subscriptions", qualifiedByName = "mapSubscriptions")
    public abstract Item toModel(ItemEntity entity);

    @Named("mapItemDetailsToEntities")
    protected List<ItemDetailsEntity> mapItemDetailsToEntities(List<ItemDetails> models) {
        return models.stream().map(itemDetailsEntityMapper::toEntity).collect(Collectors.toList());
    }

    @Named("mapSiteToEntity")
    protected SiteEntity mapSiteToEntity(Site model) {
        return siteEntityMapper.toEntity(model);
    }

    @Named("mapItemDetails")
    protected List<ItemDetails> mapItemDetails(List<ItemDetailsEntity> entities) {
        return entities.stream().map(itemDetailsEntityMapper::toModel).collect(Collectors.toList());
    }

    @Named("mapSite")
    protected Site mapSite(SiteEntity entity) {
        return siteEntityMapper.toModel(entity);
    }

    @Named("mapSubscriptions")
    protected List<Subscription> mapSubscriptions(List<SubscriptionEntity> entities) {
        return entities.stream().map(entity -> {
            var model = subscriptionEntityMapper.toModelWithoutCyclingFields(entity);
            model.setUsers(userEntityMapper.toModel(entity.getUsers()));
            return model;
        }).collect(Collectors.toList());
    };

    @AfterMapping void setCycleFields(@MappingTarget Item model) {
        model.getSubscriptions().forEach(subscription ->
                subscription.setItem(model));
    }
}
