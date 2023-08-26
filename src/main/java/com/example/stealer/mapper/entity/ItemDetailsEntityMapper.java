package com.example.stealer.mapper.entity;

import com.example.stealer.entity.ItemDetailsEntity;
import com.example.stealer.model.ItemDetails;
import org.mapstruct.Mapper;

@Mapper
public interface ItemDetailsEntityMapper {

    ItemDetails toModel(ItemDetailsEntity entity);

    ItemDetailsEntity toEntity(ItemDetails model);
}
