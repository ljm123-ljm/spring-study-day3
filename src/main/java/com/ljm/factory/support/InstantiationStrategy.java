package com.ljm.factory.support;

import com.ljm.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @Author jmle
 * @Date 2022/2/10 17:47
 * @Version 1.0
 */
public interface InstantiationStrategy {
    Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor constructor, Object[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException;
}
