package com.example.stealer.mapper.entity;

import com.example.stealer.entity.UserEntity;
import com.example.stealer.model.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface UserEntityMapper {

    User toModel(UserEntity entity);

    List<User> toModel(List<UserEntity> entities);

    UserEntity toEntity(User user);
}
