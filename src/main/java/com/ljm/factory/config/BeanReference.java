package com.ljm.factory.config;

/**
 * @Author jmle
 * @Date 2022/2/10 16:19
 * @Version 1.0
 */
public class BeanReference {

    private final String beanName;

    public BeanReference(String beanName) {
        this.beanName = beanName;
    }

    public String getBeanName() {
        return beanName;
    }
}
