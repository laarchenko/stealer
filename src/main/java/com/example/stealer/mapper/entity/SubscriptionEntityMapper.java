package com.example.stealer.mapper.entity;

import com.example.stealer.entity.SubscriptionEntity;
import com.example.stealer.model.Subscription;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface SubscriptionEntityMapper {

    @Mapping(target = "users", ignore = true)
    @Mapping(target = "item", ignore = true)
    Subscription toModelWithoutCyclingFields(SubscriptionEntity entity);

    SubscriptionEntity toEntity(Subscription model);
}
