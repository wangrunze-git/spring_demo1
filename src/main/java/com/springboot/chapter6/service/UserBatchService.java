package com.springboot.chapter6.service;

import com.springboot.chapter6.pojo.User;

import java.nio.channels.Pipe;
import java.util.List;

public interface UserBatchService {

    public int insertUsers(List<User> userList);

}
