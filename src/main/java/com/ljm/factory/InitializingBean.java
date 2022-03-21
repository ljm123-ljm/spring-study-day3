package com.ljm.factory;

/**
 * @Author jmle
 * @Date 2022/2/15 17:19
 * @Version 1.0
 */
public interface InitializingBean {
    /**
     * Bean 处理了属性填充后调用
     *
     */
    void afterPropertiesSet();
}
