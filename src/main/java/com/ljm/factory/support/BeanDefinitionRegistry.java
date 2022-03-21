package com.ljm.factory.support;

import com.ljm.factory.config.BeanDefinition;

/**
 * @Author jmle
 * @Date 2022/2/10 18:51
 * @Version 1.0
 */
public interface BeanDefinitionRegistry {
    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);

    BeanDefinition getBeanDefinition(String beanName);

    boolean containsBeanDefinition(String beanName);

    String[] getBeanDefinitionNames();
}
