package com.ljm.factory.config;

/**
 * @Author jmle
 * @Date 2022/3/2 15:21
 * @Version 1.0
 */
public interface InstantiationAwareBeanPostProcessor extends BeanPostProcessor {

    /**
     * Apply this BeanPostProcessor <i>before the target bean gets instantiated</i>.
     * The returned bean object may be a proxy to use instead of the target bean,
     * effectively suppressing default instantiation of the target bean.
     *
     * 在 Bean 对象执行初始化方法之前，执行此方法
     *
     * @param beanClass
     * @param beanName
     * @return
     */
    Object postProcessBeforeInstantiation(Class<?> beanClass,String beanName);
}
