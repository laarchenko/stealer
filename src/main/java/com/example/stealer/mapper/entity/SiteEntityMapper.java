package com.example.stealer.mapper.entity;

import com.example.stealer.entity.SiteEntity;
import com.example.stealer.model.Site;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface SiteEntityMapper {

    @Mapping(target = "items", ignore = true)
    Site toModel(SiteEntity entity);

    @Mapping(target = "items", ignore = true)
    SiteEntity toEntity(Site model);
}
