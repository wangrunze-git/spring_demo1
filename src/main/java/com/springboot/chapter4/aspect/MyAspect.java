package com.springboot.chapter4.aspect;

import com.springboot.chapter3.pojo.User;
import com.springboot.chapter4.aspect.validator.UserValidator;
import com.springboot.chapter4.aspect.validator.impl.UserValidatorImpl;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

@Aspect
public class MyAspect {
    //引入新的接口
    //value指向要增强功能的目标对象
    //defaultImpl：引入增强功能的类
    @DeclareParents(
            value = "com.springboot.chapter4.aspect.server.impl.UserServiceImpl",
            defaultImpl = UserValidatorImpl.class
    )
    public UserValidator userValidator;

    @Pointcut("execution(* com.springboot.chapter4.aspect.server.impl.UserServiceImpl.printUser(..))")
    public void pointCut(){

    }

//    @Before("execution(* com.springboot.chapter4.aspect.server.impl.UserServiceImpl.printUser(..))")
    @Before("pointCut() && args(user)")
    public void before(JoinPoint point, User user){
        Object[] args = point.getArgs();
        System.out.println("before......");
    }

//    @After("execution(* com.springboot.chapter4.aspect.server.impl.UserServiceImpl.printUser(..)")
    @After("pointCut()")
    public void after(){
        System.out.println("after......");
    }

//    @AfterReturning("execution(* com.springboot.chapter4.aspect.server.impl.UserServiceImpl.printUser(..))")
    @AfterReturning("pointCut()")
    public void afterReturning(){
        System.out.println("afterReturning......");
    }

//    @AfterThrowing("execution(* com.springboot.chapter4.aspect.server.impl.UserServiceImpl.printUser(..))")
    @AfterThrowing("pointCut()")
    public void afterThrowing(){
        System.out.println("afterThrowing......");
    }

    @Around("pointCut()")
    public void around(ProceedingJoinPoint jp) throws Throwable{
        System.out.println("around before......");
        //回调目标对象的原有方法
        jp.proceed();
        System.out.println("around after......");
    }


}
