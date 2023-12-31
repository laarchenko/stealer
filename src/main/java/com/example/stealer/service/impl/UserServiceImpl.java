package com.example.stealer.service.impl;

import com.example.stealer.mapper.entity.UserEntityMapper;
import com.example.stealer.model.User;
import com.example.stealer.repo.UserRepo;
import com.example.stealer.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final UserEntityMapper userEntityMapper;

    @Override
    public User findById(Long id) {
        return userRepo.findById(id).map(userEntityMapper::toModel).orElseThrow();//TODO UserNotFound exception
    }

    @Override
    public void save(User user) {
        userRepo.save(userEntityMapper.toEntity(user));
    }
}
