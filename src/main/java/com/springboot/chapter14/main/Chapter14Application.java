package com.springboot.chapter14.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

//定义扫描包
//因为引入JPA，所以默认情况下，需要配置数据源
//@通过@SpringBootApplication排除原有自动配置的数据源
@SpringBootApplication(scanBasePackages = "com.springboot.chapter14", exclude = {DataSourceAutoConfiguration.class})
//在WebFlux下，驱动MongoDB的JPA接口
@EnableReactiveMongoRepositories(
        //定义扫描的包
        basePackages = "com.springboot.chapter14.repository"
)
public class Chapter14Application extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.anonymous();
    }

    public static void main(String[] args) {
        SpringApplication.run(Chapter14Application.class, args);


    }
}
