package com.springboot.chapter3.config;

import com.springboot.chapter3.pojo.Apple;
import com.springboot.chapter3.pojo.User;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbcp2.BasicDataSourceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.Properties;


@Configuration
@ComponentScan(basePackages = {"com.springboot.chapter3.*"},
        excludeFilters = {@ComponentScan.Filter(classes = {Service.class})})
@PropertySource(value = {"classpath:jdbc.properties"}, ignoreResourceNotFound = true)
public class AppConfig {

    @Bean(name="dataSource")
    public DataSource getDataSource(){
        Properties properties = new Properties();
        properties.setProperty("driver", "com.mysql.jdbc.Driver");
        properties.setProperty("url", "jdbc:mysql://localhost:3306/chapter3");
        properties.setProperty("username", "root");
        properties.setProperty("password", "123456");
        DataSource dataSource = null;
        try {
            dataSource = BasicDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataSource;
    }

    @Bean(name="apple", initMethod = "init", destroyMethod = "destroy")
    public Apple getApple(){
        Apple apple = new Apple();
        apple.setName("苹果");
        return apple;
    }

}
