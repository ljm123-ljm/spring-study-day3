package com.ljm.factory;

/**
 * @Author jmle
 * @Date 2022/2/17 15:19
 * @Version 1.0
 */
public interface BeanFactoryAware extends Aware  {

    void setBeanFactory(BeanFactory beanFactory);
}
