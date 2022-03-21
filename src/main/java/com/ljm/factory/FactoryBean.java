package com.ljm.factory;

/**
 * @Author jmle
 * @Date 2022/2/22 17:50
 * @Version 1.0
 */
public interface FactoryBean<T> {

    T getObject() throws  Exception;

    Class<?> getObjectType();

    boolean isSingleton();
}
