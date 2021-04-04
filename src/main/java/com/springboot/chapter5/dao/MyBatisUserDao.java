package com.springboot.chapter5.dao;

import com.springboot.chapter5.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
//也可以使用mapper注解
//@Mapper
public interface MyBatisUserDao {

    public User getUser(Long id);

}
