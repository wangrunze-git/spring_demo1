package com.springboot.chapter6.service.impl;


import com.springboot.chapter6.dao.UserDao;
import com.springboot.chapter6.pojo.User;
import com.springboot.chapter6.service.UserService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService, ApplicationContextAware {

    @Autowired
    private UserDao userDao = null;

    private ApplicationContext applicationContext = null;

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, timeout = 1)
    public User getUser(Long id) {
        return userDao.getUser(id);
    }

    //传播行为为REQUIRES_NEW,每次调用产生新事务
    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE, timeout = 1, propagation = Propagation.REQUIRES_NEW)
    public int insertUser(User user) {
        return userDao.insertUser(user);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public int insertUsers(List<User> userList) {
        int count = 0;
        //从IoC容器中取出代理对象
        UserService userService = applicationContext.getBean(UserService.class);
        for (User user : userList) {
            /*//调用自己类自身的方法，产生自调用问题
            count += insertUser(user);*/
            //使用代理对象调用方法插入用户，此时会织入Spring数据库事务流程中
            count += userService.insertUser(user);
        }
        return count;
    }

    //实现声明周期方法，设置IoC容器
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
