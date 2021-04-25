package com.springboot.chapter11.service.impl;

import com.springboot.chapter11.dao.MyBatisUserDao;
import com.springboot.chapter11.pojo.User;
import com.springboot.chapter11.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    MyBatisUserDao myBatisUserDao = null;

    @Override
    public User insertUser(User user) {
        myBatisUserDao.insert(user);
        return user;
    }

    @Override
    public User getUser(Long id) {
        return myBatisUserDao.getUser(id);
    }

    @Override
    public List<User> findUsers(String userName, String note){
        return myBatisUserDao.findUsers(userName, note);
    }

    @Override
    public void updateUser(User user) {
        myBatisUserDao.updateUser(user);
    }

    @Override
    public int updateUserName(Long id, String userName) {
        return myBatisUserDao.updateUserName(id, userName);
    }

    @Override
    public int deleteUser(Long id) {
        return myBatisUserDao.deleteUser(id);
    }
}
