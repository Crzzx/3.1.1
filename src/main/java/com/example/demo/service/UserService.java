package com.example.demo.service;

import com.example.demo.model.User;

import java.util.List;

public interface UserService {
    void save(User user);

    void update(Long id, User updateUser);

    User findOne(long id);

    void delete(Long id);

    List<User> findAll();
}
