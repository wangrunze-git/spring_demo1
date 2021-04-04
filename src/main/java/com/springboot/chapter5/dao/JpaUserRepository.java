package com.springboot.chapter5.dao;

import com.springboot.chapter5.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

//不需要提供任何实现类，spring会根据JPA接口规范帮我们完成
public interface JpaUserRepository extends JpaRepository<User, Long> {

    @Query("from user where user_name like concat('%', ?1, '%') and note like concat('%', ?2, '%') ")
    public List<User> findUsers(String userName, String note);

    /**
     * 按用户名称模糊查询
     * @param userName 用户名
     * @return 用户列表
     */
    List<User> findByUserNameLike(String userName);

    /**
     * 根据主键查询
     * @param id --主键
     * @return 用户
     */
    User getUserById(Long id);

    /**
     * 按照用户名称或者备注进行模糊查询
     * @param userName
     * @param note
     * @return
     */
    List<User> findByUserNameLikeOrNoteLike(String userName, String note);
}
