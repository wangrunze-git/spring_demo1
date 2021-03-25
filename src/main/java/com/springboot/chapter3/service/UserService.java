package com.springboot.chapter3.service;

import com.springboot.chapter3.pojo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    public void printUser(User user){
        logger.debug("编号：{}", user.getId());
        logger.debug("用户名称：{}", user.getUserName());
        logger.debug("备注：", user.getNote());
    }
}
