package com.ljm.factory;

/**
 * @Author jmle
 * @Date 2022/2/17 15:17
 * @Version 1.0
 */
public interface BeanClassLoaderAware extends Aware {

    void setBeanClassLoader(ClassLoader classLoader);
}
