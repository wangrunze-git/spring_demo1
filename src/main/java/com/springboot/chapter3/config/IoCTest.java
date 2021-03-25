package com.springboot.chapter3.config;

import com.springboot.chapter3.pojo.*;
import com.springboot.chapter3.pojo.definition.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class IoCTest {

    private final static Logger logger = LoggerFactory.getLogger(IoCTest.class);

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);

        //测试@value获取值
        /*DataBaseProperties data = ctx.getBean(DataBaseProperties.class);
        System.out.println("------------------");
        System.out.println(data.getDriverName());
        System.out.println(data.getUrl());
        System.out.println(data.getUserName());
        System.out.println(data.getPassword());
        System.out.println("------------------");*/

        //测试从容器获取bean
        /*Person person = ctx.getBean(BussinessPerson.class);
        person.service();
        Person person1 = (Person) ctx.getBean("bussinessPerson");
        logger.info("----------------person1-----------------");
        person1.service();
        logger.info("----------------person1-----------------");*/

        //测试作用域
        /*ScopeBean scopeBean1 = ctx.getBean(ScopeBean.class);
        ScopeBean scopeBean2 = ctx.getBean(ScopeBean.class);
        logger.info("scopeBean==scopeBean2?{}",scopeBean1 == scopeBean2);*/

        //测试profile
        /*ProfileTest profileTest = ctx.getBean(ProfileTest.class);
        logger.info("test.data={}", profileTest.getData());*/

        //测试SpringEL
        TestEl testEl = ctx.getBean(TestEl.class);
        System.out.println("----------------------------");
        System.out.println(testEl.getDriver());
        System.out.println(testEl.getInitTime());
        System.out.println(testEl.getStr());
        System.out.println(testEl.getD());
        System.out.println(testEl.getPi());
        System.out.println(testEl.getOtherBeanProp());
        System.out.println(testEl.getOtherBeanprop1());
        System.out.println(testEl.getRun());
        System.out.println(testEl.isPiFlag());
        System.out.println(testEl.isStrFlag());
        System.out.println(testEl.getStrApp());
        System.out.println(testEl.getResultDesc());

    }


}
