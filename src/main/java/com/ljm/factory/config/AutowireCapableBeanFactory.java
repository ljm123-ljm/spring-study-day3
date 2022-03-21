package com.ljm.factory.config;

import com.ljm.factory.BeanFactory;

/**
 * @Author jmle
 * @Date 2022/2/11 16:59
 * @Version 1.0
 */
public interface AutowireCapableBeanFactory extends BeanFactory {
    /**
     * 执行BeanPostProcess接口实现类的postProcessBeforeInitialization 方法
     *
     * @param existingBean
     * @param beanName
     * @return Result<XxxxDO>
     */
    Object applyBeanPostProcessorBeforeInitialization(Object existingBean, String beanName);


    /**
     * 执行 BeanPostProcessors 接口实现类的 postProcessorsAfterInitialization 方法
     *
     * @param existingBean
     * @param beanName
     * @return Result<XxxxDO>
     */
    Object applyBeanPostProcessAfterInitialization(Object existingBean, String beanName);

}
