package com.ljm.factory.config;

import com.ljm.factory.ConfigurableListableBeanFactory;

import java.io.IOException;
import java.net.MalformedURLException;

/**
 * @Author jmle
 * @Date 2022/2/14 16:50
 * @Version 1.0
 */
public interface BeanFactoryPostProcess {

    /**
     * 在所有的 BeanDefinition 加载完成后，实例化 Bean 对象之前，提供修改 BeanDefinition 属性的机制
     *
     * @param beanFactory
     */
    void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws IOException;
}
