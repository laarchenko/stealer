package com.example.stealer.mapper;

import com.example.stealer.entity.ItemEntity;
import com.example.stealer.model.Item;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ItemEntityMapper {

    @Mapping(target = "site", ignore = true) //TODO
    ItemEntity toEntity(Item item);

    Item toModel(ItemEntity entity);
}
