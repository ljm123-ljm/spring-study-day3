package com.ljm.factory.support;

import com.ljm.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @Author jmle
 * @Date 2022/2/10 17:48
 * @Version 1.0
 */
public class SimpleInstantiationStrategy implements InstantiationStrategy {
    @Override
    public Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor constructor, Object[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class clazz = beanDefinition.getBeanClass();
        if (null != clazz) {
            return clazz.getDeclaredConstructor(constructor.getParameterTypes()).newInstance(args);
        } else {
            return clazz.getDeclaredConstructor().newInstance();
        }
    }
}
