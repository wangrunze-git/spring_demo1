package com.springboot.chapter12.config;

import com.springboot.chapter3.pojo.DataBaseProperties;
import org.apache.commons.dbcp2.BasicDataSourceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
public class AppConfig implements WebMvcConfigurer {
    @Bean(name="dataSource_12")
    public DataSource getDevDataSource(){
        Properties properties = new Properties();
        properties.setProperty("driver", "com.mysql.jdbc.Driver");
        properties.setProperty("url", "jdbc:mysql://localhost:3306/chapter5");
        properties.setProperty("username", "root");
        properties.setProperty("password", "wrz123");
        DataSource dataSource = null;
        try {
            dataSource = BasicDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("-----------(dev)DataSource创建成功-----------");
        return dataSource;
    }

    //增加映射关系
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //使得/login/page映射为login.jsp
        registry.addViewController("/login/page").setViewName("login");
        //使得/logout/page映射为logout_welcome.jsp
        registry.addViewController("/logout/page").setViewName("logout");
        //使得/logout映射为logout.jsp
        registry.addViewController("/welcome").setViewName("welcome1");
    }
}
