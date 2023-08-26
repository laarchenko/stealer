package com.example.stealer.mapper.entity;

import com.example.stealer.entity.ItemDetailsEntity;
import com.example.stealer.entity.ItemEntity;
import com.example.stealer.model.Item;
import com.example.stealer.model.Price;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
public abstract class ItemEntityMapper {

    @Autowired
    PriceEntityMapper priceEntityMapper;

    @Autowired
    SiteEntityMapper siteEntityMapper;

    public abstract ItemEntity toEntity(Item item);

    public abstract Item toModel(ItemEntity entity);

    @Named("mapPricesToEntities")
    List<ItemDetailsEntity> mapPricesToEntities(List<Price> models) {
        return models.stream().map(priceEntityMapper::toEntity).collect(Collectors.toList());
    }

    @Named("mapPricesToModels")
    List<Price> mapPricesToModels(List<ItemDetailsEntity> entities) {
        return entities.stream().map(priceEntityMapper::toModel).collect(Collectors.toList());
    }
}
