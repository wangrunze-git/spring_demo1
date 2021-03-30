package com.springboot.chapter4.jdbc;

import com.springboot.chapter3.pojo.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDao {
    public int insertUser(Connection conn, User user) throws SQLException{
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("insert into t_user(user_name, note) values (?, ?)");
            ps.setString(1, user.getUserName());
            ps.setString(2, user.getNote());
            return ps.executeUpdate();
        }finally {
            ps.close();
        }
    }
}
