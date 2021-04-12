package com.springboot.chapter8.repository;

import com.springboot.chapter8.pojo.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

//标识为DAO层
@Repository
//扩展MongoRepository
public interface UserRepository extends MongoRepository<User, Long> {

    /**
     * 符合JPA规范命名方法，则不需要再实现该方法也可使用
     * 意在对满足条件的文件按照用户名称进行模糊查询
     * @param userName -- 用户名称
     * @return 满足条件的用户信息
     */
    List<User> findByUserNameLike(String userName);

    /**
     * 使用id和用户名称查询
     * 注解@Query阿拉伯数字指定参数的下标，以0开始
     * @param id -- 编号
     * @param userName
     * @return 用户信息
     */
    @Query("{'id': ?0, 'userName': ?1}")
    User find(Long id, String userName);

    /**
     * 根据编号或者用户名查用户
     * @param id -- 编号
     * @param userName -- 用户名
     * @return 用户信息
     */
    User findUserByIdOrUserName(Long id, String userName);

}
