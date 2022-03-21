package com.ljm.factory;

import java.util.Map;

/**
 * @Author jmle
 * @Date 2022/2/11 17:01
 * @Version 1.0
 */
public interface ListableBeanFactory extends BeanFactory {
    /**
     * 按照类型返回Bean实例
     *
     */
    <T> Map<String,T> getBeansOfType(Class<T> type);
    /**
     * 返回注册表中所有Bean的名称
     *
     */
    String[] getBeanDefinitionNames();
}
