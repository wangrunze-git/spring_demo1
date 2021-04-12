package com.springboot.chapter8.main;

import com.springboot.chapter7.main.Chapter7Main;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Repository;

@SpringBootApplication(scanBasePackages = {"com.springboot.chapter8"})
@EnableCaching
//指定扫描的包，用于扫描既成了MongoRepository的接口
@EnableMongoRepositories(
        //扫描包
        basePackages = "com.springboot.chapter8.repository"
        //使用自定义后缀，其默认值为Impl
        //此时需要修改类名：UserRepositoryImpl --> UserRepositoryStuff
        //repositoryImplementationPostfix = "Stuff"
)
public class Cahpter8Main {
    public static void main(String[] args) {
        SpringApplication.run(Cahpter8Main.class, args);
    }
}
