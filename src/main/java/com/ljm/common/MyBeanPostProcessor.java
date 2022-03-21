package com.ljm.common;

import com.ljm.bean.UserService;
import com.ljm.factory.config.BeanPostProcessor;

/**
 * @Author jmle
 * @Date 2022/2/14 18:53
 * @Version 1.0
 */
public class MyBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        if ("userService".equals(beanName)) {
            UserService userService = (UserService) bean;
            userService.setLocation("改为北京");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        return bean;
    }
}
