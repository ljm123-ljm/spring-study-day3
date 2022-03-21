package com.ljm.factory.support;

import cn.hutool.core.util.StrUtil;
import com.ljm.factory.DisposableBean;
import com.ljm.factory.config.BeanDefinition;

import java.lang.reflect.Method;

/**
 * @Author jmle
 * @Date 2022/2/15 17:11
 * @Version 1.0
 */
public class DisposableBeanAdapter implements DisposableBean {

    private final Object bean;

    private final String beanName;

    private String destroyMethodName;

    public DisposableBeanAdapter(Object bean, String beanName, BeanDefinition beanDefinition) {
        this.bean = bean;
        this.beanName = beanName;
        this.destroyMethodName = beanDefinition.getDestroyMethodName();
    }


    @Override
    public void destroy() throws Exception {
        //1.判断实现接口DisposableBean
        if (bean instanceof DisposableBean) {
            ((DisposableBean) bean).destroy();
        }
        if(StrUtil.isNotEmpty(destroyMethodName) && !(bean instanceof  DisposableBean && "destroy".equals(this.destroyMethodName))){
            Method destroyMethod = bean.getClass().getMethod(destroyMethodName);
            destroyMethod.invoke(bean);
        }
    }
}
