package com.springboot.chapter4.test;

import com.springboot.chapter3.pojo.User;
import com.springboot.chapter4.jdbc.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class JdbcTest {
    private static final Logger logger = LoggerFactory.getLogger(JdbcTest.class);

    //FIXME 该变量因为是静态的，所以不会注入进来，spring会跳过静态的变量
    private static UserService userService = null;

    public JdbcTest(@Autowired UserService userService) {
        this.userService = userService;
    }

    public static void main(String[] args) {
        int i = userService.insertUser();
        logger.info("------------userService插入了{}条记录", i);
    }
}
