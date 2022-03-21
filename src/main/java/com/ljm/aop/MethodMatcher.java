package com.ljm.aop;

import java.lang.reflect.Method;

/**
 * @Author jmle
 * @Date 2022/2/25 14:03
 * @Version 1.0
 */
public interface MethodMatcher {

    boolean matches(Method mothod, Class<?> targetClass);
}
