package com.example.stealer.mapper.entity;

import com.example.stealer.entity.UserEntity;
import com.example.stealer.model.User;
import org.mapstruct.Mapper;

@Mapper
public interface UserEntityMapper {

    User toModel(UserEntity entity);

    UserEntity toEntity(User user);
}
