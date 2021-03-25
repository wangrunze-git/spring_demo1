package com.springboot.chapter3.config;

import com.springboot.chapter3.pojo.BussinessPerson;
import com.springboot.chapter3.pojo.DataBaseProperties;
import com.springboot.chapter3.pojo.User;
import com.springboot.chapter3.pojo.definition.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class IoCTest {

    private final static Logger logger = LoggerFactory.getLogger(IoCTest.class);

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        DataBaseProperties data = ctx.getBean(DataBaseProperties.class);
        System.out.println("------------------");
        System.out.println(data.getDriverName());
        System.out.println(data.getUrl());
        System.out.println(data.getUserName());
        System.out.println(data.getPassword());
        System.out.println("------------------");
        /*Person person = ctx.getBean(BussinessPerson.class);
        person.service();
        Person person1 = (Person) ctx.getBean("bussinessPerson");
        logger.info("----------------person1-----------------");
        person1.service();
        logger.info("----------------person1-----------------");*/

    }


}
