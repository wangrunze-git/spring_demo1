package com.springboot.chapter11.service;

import com.springboot.chapter11.pojo.User;

import java.util.List;

public interface UserService {
    User insertUser(User user);

    User getUser(Long id);

    List<User> findUsers(String userName, String note);

    void updateUser(User user);

    int updateUserName(Long id, String userName);

    int deleteUser(Long id);
}
