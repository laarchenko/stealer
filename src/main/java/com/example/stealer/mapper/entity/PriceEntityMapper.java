package com.example.stealer.mapper.entity;

import com.example.stealer.entity.ItemDetailsEntity;
import com.example.stealer.model.Price;
import org.mapstruct.Mapper;

@Mapper
public interface PriceEntityMapper {

    Price toModel(ItemDetailsEntity entity);

    ItemDetailsEntity toEntity(Price model);
}
