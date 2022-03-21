package com.ljm.aop;

/**
 * @Author jmle
 * @Date 2022/2/25 14:02
 * @Version 1.0
 */
public interface ClassFilter {

    boolean matches(Class<?> clazz);
}
