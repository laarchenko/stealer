package com.example.stealer.service;

import com.example.stealer.model.User;

public interface UserService {

    User findById(Long id);

    void save(User user);
}
