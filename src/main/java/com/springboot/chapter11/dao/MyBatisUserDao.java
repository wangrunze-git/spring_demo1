package com.springboot.chapter11.dao;


import com.springboot.chapter11.pojo.User;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
//也可以使用mapper注解
//@Mapper
public interface MyBatisUserDao {

    public int insert(User user);

    User getUser(Long id);

    List<User> findUsers(@Param("userName") String userName, @Param("note")String note);

    void updateUser(User user);

    int updateUserName(Long id, String userName);

    int deleteUser(Long id);
}
