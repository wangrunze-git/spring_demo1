package com.example.demo;

import com.springboot.chapter4.aspect.MyAspect;
import com.springboot.chapter4.aspect.MyAspect1;
import com.springboot.chapter4.aspect.MyAspect2;
import com.springboot.chapter4.aspect.MyAspect3;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.logging.Logger;

//指定扫描的包
@SpringBootApplication(scanBasePackages = {"com.springboot.chapter4.aspect"})
public class DemoApplication {

    //定义切面
    @Bean(name = "myAspect")
    public MyAspect initMyAspect(){
        return new MyAspect();
    }

    @Bean(name="myAspect2")
    public MyAspect2 initMyAspect2(){
        return new MyAspect2();
    }
    @Bean(name="mAspect1")
    public MyAspect1 initMyAspect1(){
        return new MyAspect1();
    }
    @Bean(name="myAspect3")
    public MyAspect3 initMyAspect3(){
        return new MyAspect3();
    }

    //启动切面
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}
