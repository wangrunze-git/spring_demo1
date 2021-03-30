package com.springboot.chapter4.test;

import com.springboot.chapter4.interceptor.MyInterceptor;
import com.springboot.chapter4.proxy.ProxyBean;
import com.springboot.chapter4.service.HelloService;
import com.springboot.chapter4.service.impl.HelloServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class ProxyTest {
    private final static Logger logger = LoggerFactory.getLogger(ProxyTest.class);

    @Autowired JdbcTest jdbcTest;

    public static void main(String[] args) {
        /*HelloService helloService = new HelloServiceImpl();
        //按约定获取proxy
        HelloService proxy = (HelloService)ProxyBean.getProxyBean(helloService, new MyInterceptor());
        proxy.sayHello("zhangsan");
        System.out.println("\n#####################name is null!#########################\n");
        proxy.sayHello(null);*/



    }
}
