package com.springboot.chapter3.pojo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Apple {

    private static final Logger logger = LoggerFactory.getLogger(Apple.class);

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void init(){
        logger.info("apple的初始化方法");
    }

    public void destroy(){
        logger.info("apple的销毁方法");
    }
}
