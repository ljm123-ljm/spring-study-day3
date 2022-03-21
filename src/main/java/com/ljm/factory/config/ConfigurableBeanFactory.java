package com.ljm.factory.config;

import com.ljm.factory.HierarchicalBeanFactory;

/**
 * @Author jmle
 * @Date 2022/2/11 17:05
 * @Version 1.0
 */
public interface ConfigurableBeanFactory extends HierarchicalBeanFactory, SingletonBeanRegistry {
    String SCOPE_SINGLETON = "singleton";
    String SCOPE_PROTOTYPE = "prototype";

    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);

    void destroySingletons();
}
