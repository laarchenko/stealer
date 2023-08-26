package com.example.stealer.mapper.entity;

import com.example.stealer.entity.SiteEntity;
import com.example.stealer.model.Site;
import org.mapstruct.Mapper;

@Mapper
public interface SiteEntityMapper {

    Site toModel(SiteEntity entity);

    SiteEntity toEntity(Site model);
}
