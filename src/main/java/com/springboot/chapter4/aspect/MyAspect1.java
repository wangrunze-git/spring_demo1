package com.springboot.chapter4.aspect;

import org.aspectj.lang.annotation.*;
import org.springframework.core.Ordered;

@Aspect
//@Order(1)
public class MyAspect1 implements Ordered {

    @Override
    public int getOrder() {
        return 1;
    }

    @Pointcut("execution(* com.springboot.chapter4.aspect.server.impl.UserServiceImpl.manyAspects(..))")
    public void manyAspects(){

    }

    @Before("manyAspects()")
    public void before(){
        System.out.println("MyAspect1 before......");
    }

    @After("manyAspects()")
    public void after(){
        System.out.println("MyAspect1 after......");
    }

    @AfterReturning("manyAspects()")
    public void afterReturning(){
        System.out.println("MyAspect1 afterReturning......");
    }


}
