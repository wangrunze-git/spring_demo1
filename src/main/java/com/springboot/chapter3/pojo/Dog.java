package com.springboot.chapter3.pojo;

import com.springboot.chapter3.pojo.definition.Animal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
//@Primary
public class Dog implements Animal {

    private final static Logger logger = LoggerFactory.getLogger(Dog.class);
    @Override
    public void use() {
        logger.info("狗【{}】会看门！", Dog.class.getSimpleName());
    }

}
