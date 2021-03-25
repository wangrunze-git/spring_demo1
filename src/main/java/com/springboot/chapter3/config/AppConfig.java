package com.springboot.chapter3.config;

import com.springboot.chapter3.pojo.Apple;
import com.springboot.chapter3.pojo.DataBaseProperties;
import com.springboot.chapter3.pojo.User;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbcp2.BasicDataSourceFactory;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.Properties;


@Configuration
@ComponentScan(basePackages = {"com.springboot.chapter3.*"},
        excludeFilters = {@ComponentScan.Filter(classes = {Service.class})})
@PropertySource(value = {"classpath:jdbc.properties", "classpath:application.properties",
        "classpath:application-test.properties", "classpath:application-dev.properties"},
        ignoreResourceNotFound = true)
@ImportResource(value = {"classpath:spring-other.xml"})//通过xml注入bean
public class AppConfig {

    @Bean(name="dataSource")
    @Conditional(DataBaseProperties.class)
    @Profile("dev")
    public DataSource getDevDataSource(){
        Properties properties = new Properties();
        properties.setProperty("driver", "com.mysql.jdbc.Driver");
        properties.setProperty("url", "jdbc:mysql://localhost:3306/dev_spring_boot");
        properties.setProperty("username", "root");
        properties.setProperty("password", "123456");
        DataSource dataSource = null;
        try {
            dataSource = BasicDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("-----------(dev)DataSource创建成功-----------");
        return dataSource;
    }

    @Bean(name="dataSource")
    @Conditional(DataBaseProperties.class)
    @Profile("test")
    public DataSource getTestDataSource(){
        Properties properties = new Properties();
        properties.setProperty("driver", "com.mysql.jdbc.Driver");
        properties.setProperty("url", "jdbc:mysql://localhost:3306/test_spring_boot");
        properties.setProperty("username", "root");
        properties.setProperty("password", "123456");
        DataSource dataSource = null;
        try {
            dataSource = BasicDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("-----------(test)DataSource创建成功-----------");
        return dataSource;
    }

    @Bean(name="apple", initMethod = "init", destroyMethod = "destroy")
    public Apple getApple(){
        Apple apple = new Apple();
        apple.setName("苹果");
        return apple;
    }

}
