package com.ljm.context.support;

import com.ljm.context.ApplicationContext;
import com.ljm.context.ApplicationContextAware;
import com.ljm.factory.config.BeanPostProcessor;

/**
 * @Author jmle
 * @Date 2022/2/17 15:24
 * @Version 1.0
 */
public class ApplicationContextAwareProcessor implements BeanPostProcessor {

    private final ApplicationContext applicationContext;

    public ApplicationContextAwareProcessor(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        if(bean instanceof ApplicationContextAware){
            ((ApplicationContextAware) bean).setApplicationContext(applicationContext);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
       return bean;
    }
}
