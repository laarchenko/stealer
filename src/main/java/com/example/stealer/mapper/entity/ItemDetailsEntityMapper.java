package com.example.stealer.mapper.entity;

import com.example.stealer.entity.ItemDetailsEntity;
import com.example.stealer.model.ItemDetails;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper
public interface ItemDetailsEntityMapper {

    ItemDetails toModel(ItemDetailsEntity entity);

    ItemDetailsEntity toEntity(ItemDetails model);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "item", ignore = true)
    void updateFromModel(@MappingTarget ItemDetailsEntity entity, ItemDetails model);
}
