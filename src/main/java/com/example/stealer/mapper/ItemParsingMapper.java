package com.example.stealer.mapper;

import com.example.stealer.entity.ItemEntity;
import com.example.stealer.model.Item;
import com.example.stealer.model.ItemParsingRequest;
import com.example.stealer.model.ItemParsingResult;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper
public interface ItemParsingMapper {

    void updateModel(@MappingTarget Item item, ItemParsingResult itemParsingResult);

    @Mapping(target = "enabled", source = "site.enabled")
    @Mapping(target = "siteName", source = "site.value")
    ItemParsingRequest toRequest(ItemEntity entity);

    Item toModel(ItemParsingResult result);
}
