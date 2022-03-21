package com.ljm.factory.config;

/**
 * @Author jmle
 * @Date 2022/2/10 16:20
 * @Version 1.0
 */
public interface SingletonBeanRegistry {

    Object getSingleton(String beanName);

    void registerSingleton(String beanName,Object singletonObject);
}
