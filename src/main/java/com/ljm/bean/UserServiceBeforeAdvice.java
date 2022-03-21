package com.ljm.bean;

import com.ljm.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * @Author jmle
 * @Date 2022/3/2 16:02
 * @Version 1.0
 */
public class UserServiceBeforeAdvice implements MethodBeforeAdvice {
    @Override
    public void before(Method method, Object[] args, Object target) {
        System.out.println("拦截方法: "+method.getName());
    }
}
