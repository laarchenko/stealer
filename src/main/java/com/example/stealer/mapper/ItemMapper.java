package com.example.stealer.mapper;

import com.example.stealer.model.Item;
import com.example.stealer.model.ItemParsingResult;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper
public interface ItemMapper {

    void updateModel(@MappingTarget Item item, ItemParsingResult itemParsingResult);
}
