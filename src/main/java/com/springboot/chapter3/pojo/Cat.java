package com.springboot.chapter3.pojo;

import com.springboot.chapter3.pojo.definition.Animal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class Cat implements Animal {

    private static final Logger logger = LoggerFactory.getLogger(Cat.class);

    @Override
    public void use() {
        logger.info("猫【{}】会捉老鼠！", Cat.class.getSimpleName());
    }
}
