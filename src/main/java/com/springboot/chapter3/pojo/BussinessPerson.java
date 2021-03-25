package com.springboot.chapter3.pojo;

import com.springboot.chapter3.pojo.definition.Animal;
import com.springboot.chapter3.pojo.definition.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;


//@Component
public class BussinessPerson implements Person, BeanNameAware, BeanFactoryAware, ApplicationContextAware,
        InitializingBean, DisposableBean {

    private static final Logger logger = LoggerFactory.getLogger(BussinessPerson.class);

    private Animal animal = null;

    public BussinessPerson(@Autowired @Qualifier("dog") Animal animal) {
        this.animal = animal;
    }

    @Override
    public void service() {
        this.animal.use();
    }

    @Override
    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        logger.info("【{}调用BeanFactoryAware的setBeanFactory方法】", this.getClass().getSimpleName());
    }

    @Override
    public void setBeanName(String s) {
        logger.info("【{}调用BeanNameAware的setBeanName方法】", this.getClass().getSimpleName());
    }

    @Override
    public void destroy() throws Exception {
        logger.info("【{}调用DisposableBean的destroy方法】", this.getClass().getSimpleName());
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        logger.info("【{}调用InitializingBean的afterPropertiesSet方法】", this.getClass().getSimpleName());
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        logger.info("【{}调用ApplicationContextAware的setApplicationContext方法】", this.getClass().getSimpleName());
    }

    @PostConstruct
    public void init(){
        logger.info("【{}调用注解@PostConstract定义的自定义初始化方法】", this.getClass().getSimpleName());
    }

    @PreDestroy
    public void destroy1(){
        logger.info("【{}调用注解@PreDestroy定义的自定义销毁方法】", this.getClass().getSimpleName());
    }
}
