package com.ljm.factory.support;

import com.ljm.factory.DisposableBean;
import com.ljm.factory.config.SingletonBeanRegistry;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author jmle
 * @Date 2022/2/10 17:45
 * @Version 1.0
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    protected static  final Object NULL_OBJECT=new Object();

    private final Map<String, Object> singletonObjects = new ConcurrentHashMap<>();

    private final Map<String,DisposableBean> disposableBeans=new LinkedHashMap<>();

    @Override
    public Object getSingleton(String beanName) {
        return singletonObjects.get(beanName);
    }

    @Override
    public void registerSingleton(String beanName, Object singletonObject) {
        singletonObjects.put(beanName,singletonObject);
    }

    protected void addSingleton(String beanName, Object singletonObject) {
        singletonObjects.put(beanName, singletonObject);
    }

    public void registerDisposableBean(String beanName, DisposableBean bean){
        disposableBeans.put(beanName,bean);
    }

    public void destroySingletons(){
        Set<String> keySet = this.disposableBeans.keySet();
        Object[] disposableBeanNames = keySet.toArray();
        for (int i = disposableBeanNames.length - 1; i >= 0; i--) {
            try {
                Object beanName = disposableBeanNames[i];
                DisposableBean disposableBean = disposableBeans.remove(beanName);
                disposableBean.destroy();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }
}
