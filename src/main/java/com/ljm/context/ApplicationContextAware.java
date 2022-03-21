package com.ljm.context;

import com.ljm.factory.Aware;

/**
 * @Author jmle
 * @Date 2022/2/17 15:22
 * @Version 1.0
 */
public interface ApplicationContextAware extends Aware {
    void setApplicationContext(ApplicationContext applicationContext);
}
