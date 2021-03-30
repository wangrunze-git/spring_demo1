package com.springboot.chapter4.aspect.server.impl;

import com.springboot.chapter3.pojo.User;
import com.springboot.chapter4.aspect.server.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
//不实现接口，测试cglib代理
//public class UserServiceImpl{
    @Override
    public void printUser(User user) {
        if (user == null){
            throw new RuntimeException("检查用户参数是否为空......");
        }
        System.out.println("id=" + user.getId());
        System.out.println("\tusername =" + user.getUserName());
        System.out.println("\tnote =" + user.getNote());
    }

    @Override
    public void manyAspects() {
        System.out.println("测试多个切面顺序");
    }
}
