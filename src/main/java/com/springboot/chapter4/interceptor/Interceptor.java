package com.springboot.chapter4.interceptor;


import com.springboot.chapter4.invoke.Invocation;

import java.lang.reflect.InvocationTargetException;

public interface Interceptor {

    //事前方法
    public boolean before();

    //事后方法
    public void after();

    //取代原有事件方法
    public Object around(Invocation invocation)
        throws InvocationTargetException, IllegalAccessException;

    //是否返回方法，事件没有发生异常执行
    public void afterReturning();

    //事后异常方法，当事件发生异常后执行
    public void afterThrowing();

    //是否使用around方法取代原有方法
    boolean useAround();


}
