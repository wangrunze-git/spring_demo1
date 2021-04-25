package com.springboot.chapter10.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//声明配置类
@Configuration
//定制扫描路径
@SpringBootApplication(scanBasePackages = {"com.springboot.chapter10", "com.springboot.chapter8"})
public class Chapter10Application implements WebMvcConfigurer {

    public static void main(String[] args) {
        SpringApplication.run(Chapter10Application.class, args);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册拦截器到Spring MVC机制，然后它会返回一个拦截器注册
        InterceptorRegistration ir = registry.addInterceptor(new Interceptor1());
        //指定拦截匹配模式，限制拦截器拦截请求
        ir.addPathPatterns("/interceptor/*");

        InterceptorRegistration ir2 = registry.addInterceptor(new Interceptor2());
        ir2.addPathPatterns("/interceptor/*");

        InterceptorRegistration ir3 = registry.addInterceptor(new Interceptor3());
        ir3.addPathPatterns("/interceptor/*");

    }
}
