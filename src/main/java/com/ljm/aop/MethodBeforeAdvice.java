package com.ljm.aop;

import java.lang.reflect.Method;

/**
 * @Author jmle
 * @Date 2022/3/2 15:11
 * @Version 1.0
 */
public interface MethodBeforeAdvice extends BeforeAdvice {

    void before(Method method, Object [] args, Object target);
}
