package com.springboot.chapter4.jdbc;

import com.springboot.chapter3.pojo.User;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

@Service
public class UserService {
    public int insertUser(){
        UserDao userdao = new UserDao();
        User user = new User();
        user.setUserName("user_name_1");
        user.setNote("note_1");
        Connection conn = null;
        int result = 0;
        try {
            //获取数据库事务连接
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/test_database", "root", "wrz123");
            //非自动提交事务
            conn.setAutoCommit(false);
            result = userdao.insertUser(conn, user);
            //提交事务
            conn.commit();
        } catch (Exception e) {
            try {
                //回滚事务
                conn.rollback();
            }catch (SQLException ex){
                ex.printStackTrace();
            }
            e.printStackTrace();
        }finally {
            //释放数据库连接资源
            if (conn != null){
                try {
                    conn.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }
        return result;
    }
}
