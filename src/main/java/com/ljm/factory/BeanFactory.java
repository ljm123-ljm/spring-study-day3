package com.ljm.factory;

/**
 * @Author jmle
 * @Date 2022/2/10 16:24
 * @Version 1.0
 */
public interface BeanFactory {
    Object getBean(String name);

    Object getBean(String name, Object... args);

    <T> T getBean(String name,Class<T> requiredType);
}
