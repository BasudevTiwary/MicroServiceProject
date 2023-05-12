package com.userService2.UserService2.service;

import com.userService2.UserService2.entities.User;

import java.util.List;

public interface UserService {
    void addUser(User user);

    List<User> getAllUsers();

//    User getOneUser(int userId);

    User getOneUserByUserName(String userName);
}
