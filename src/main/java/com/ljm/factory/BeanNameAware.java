package com.ljm.factory;

/**
 * @Author jmle
 * @Date 2022/2/17 15:20
 * @Version 1.0
 */
public interface BeanNameAware extends Aware {
    void setBeanName(String name);
}
