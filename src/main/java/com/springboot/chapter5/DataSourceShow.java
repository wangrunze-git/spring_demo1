package com.springboot.chapter5;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.sql.DataSource;

public class DataSourceShow implements ApplicationContextAware {

    ApplicationContext applicationContext = null;

    //Spring容器会自动调用这个方法，注入Spring IoC容器
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
        DataSource dataSource = applicationContext.getBean(DataSource.class);
        System.out.println("---------------------------------");
        System.out.println(dataSource.getClass().getName());
        System.out.println("---------------------------------");
    }
}
