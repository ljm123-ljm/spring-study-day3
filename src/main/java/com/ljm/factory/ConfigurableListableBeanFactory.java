package com.ljm.factory;

import com.ljm.factory.config.AutowireCapableBeanFactory;
import com.ljm.factory.config.BeanDefinition;
import com.ljm.factory.config.BeanPostProcessor;
import com.ljm.factory.config.ConfigurableBeanFactory;

/**
 * @Author jmle
 * @Date 2022/2/11 17:00
 * @Version 1.0
 */
public interface ConfigurableListableBeanFactory extends ListableBeanFactory, AutowireCapableBeanFactory, ConfigurableBeanFactory {
    /**
     * 获取Bean的信息
     */
    BeanDefinition getBeanDefinition(String beanName);


    void preInstantiateSingletons();

    @Override
    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);
}
