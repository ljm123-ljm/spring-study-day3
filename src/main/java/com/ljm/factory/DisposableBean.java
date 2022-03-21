package com.ljm.factory;

/**
 * @Author jmle
 * @Date 2022/2/15 17:04
 * @Version 1.0
 */
public interface DisposableBean {
    void destroy() throws Exception;
}
