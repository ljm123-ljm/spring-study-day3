package com.ljm.common;

import com.ljm.factory.ConfigurableListableBeanFactory;
import com.ljm.factory.config.BeanDefinition;
import com.ljm.factory.config.BeanFactoryPostProcess;
import com.ljm.props.PropertyValue;
import com.ljm.props.PropertyValues;

/**
 * @Author jmle
 * @Date 2022/2/14 18:54
 * @Version 1.0
 */
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcess {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) {
        BeanDefinition beanDefinition = beanFactory.getBeanDefinition("userService");
        PropertyValues propertyValues = beanDefinition.getPropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("company", "改为:携程"));
    }
}
