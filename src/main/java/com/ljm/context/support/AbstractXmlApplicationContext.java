package com.ljm.context.support;

import com.ljm.factory.support.DefaultListableBeanFactory;
import com.ljm.xml.XmlBeanDefinitionReader;

/**
 * @Author jmle
 * @Date 2022/2/14 18:45
 * @Version 1.0
 */
public abstract class AbstractXmlApplicationContext extends AbstractRefreshableApplicationContext {

    @Override
    protected void loadBeanDefinitions(DefaultListableBeanFactory beanFactory) {
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory, this);
        String[] configLocations = getConfigLocations();
        if (null != configLocations) {
            beanDefinitionReader.loadBeanDefinitions(configLocations);
        }
    }

    protected abstract String[] getConfigLocations();
}
