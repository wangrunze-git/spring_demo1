package com.springboot.chapter6.service.impl;

import com.springboot.chapter6.service.JdbcService;
import org.apache.ibatis.session.TransactionIsolationLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Service
public class JdbcServiceImpl implements JdbcService {

    //使用jdbc的方式操作数据库，手动控制事务，连接的关闭

    @Autowired
    private DataSource dataSource = null;

    @Override
    public int inserUser(String userName, String note) {
        Connection conn = null;
        int result = 0;
        try {
            //获取连接
            conn = dataSource.getConnection();
            //开启事务
            conn.setAutoCommit(false);
            //设置隔离级别
            conn.setTransactionIsolation(TransactionIsolationLevel.READ_COMMITTED.getLevel());
            //执行SQL
            PreparedStatement ps = conn.prepareStatement("insert into t_user(user_name, note ) values (?, ?)");
            ps.setString(1, userName);
            ps.setString(2, note);
            result = ps.executeUpdate();
            //提交事务
            conn.commit();
        }catch (Exception e){
            //回滚事务
            if (conn != null){
                try {
                    conn.rollback();
                }catch (SQLException e1){
                    e1.printStackTrace();
                }
            }
            e.printStackTrace();
        }finally {
            //关闭数据库连接
            try {
                if (conn != null && !conn.isClosed()){
                    conn.close();
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        return result;
    }
}
