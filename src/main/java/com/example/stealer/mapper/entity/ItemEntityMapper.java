package com.example.stealer.mapper.entity;

import com.example.stealer.entity.ItemDetailsEntity;
import com.example.stealer.entity.ItemEntity;
import com.example.stealer.entity.SiteEntity;
import com.example.stealer.model.Item;
import com.example.stealer.model.ItemDetails;
import com.example.stealer.model.Site;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
public abstract class ItemEntityMapper {

    @Autowired
    ItemDetailsEntityMapper itemDetailsEntityMapper;

    @Autowired
    SiteEntityMapper siteEntityMapper;

    public abstract ItemEntity toEntity(Item item);

    @Mapping(target = "itemDetails", source = "itemDetails", qualifiedByName = "mapItemDetails")
    @Mapping(target = "site", source = "site", qualifiedByName = "mapSite")
    public abstract Item toModel(ItemEntity entity);

    @Named("mapItemDetails")
    protected List<ItemDetails> mapItemDetails(List<ItemDetailsEntity> entities) {
        return entities.stream().map(itemDetailsEntityMapper::toModel).collect(Collectors.toList());
    }

    @Named("mapSite")
    protected Site mapSite(SiteEntity entity) {
        return siteEntityMapper.toModel(entity);
    }
}
