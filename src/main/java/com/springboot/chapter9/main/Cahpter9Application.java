package com.springboot.chapter9.main;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Repository;

//定制扫描路径
@SpringBootApplication(scanBasePackages = {"com.springboot.chapter9", "com.springboot.chapter8"})
//扫描MyBatis的DAO接口
@MapperScan(basePackages = {"com.springboot.chapter9", "com.springboot.chapter8"},
        annotationClass = Repository.class)
public class Cahpter9Application {
    public static void main(String[] args) {
        SpringApplication.run(Cahpter9Application.class, args);
    }
}
