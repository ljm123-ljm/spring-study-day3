package com.ljm.factory.support;

import com.ljm.factory.FactoryBean;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author jmle
 * @Date 2022/2/22 18:07
 * @Version 1.0
 */
public abstract class FactoryBeanRegistrySupport extends DefaultSingletonBeanRegistry {

    private final Map<String,Object> factoryBeanObjectCache=new ConcurrentHashMap<String,Object>();

    protected Object getCacheObjectForFactoryBean(String beanName){
        Object object = this.factoryBeanObjectCache.get(beanName);
        return (object!=NULL_OBJECT ?object :null);
    }
    protected Object getObjectFromFactoryBean(FactoryBean factory,String beanName){
        if(factory.isSingleton()){
            Object object = this.factoryBeanObjectCache.get(beanName);
            if(object==null){
                object=doGetObjectFromFactoryBean(factory,beanName);
                this.factoryBeanObjectCache.put(beanName,(object!=null ?object :NULL_OBJECT));
            }
            return (object!=NULL_OBJECT ? object :null);
        }else {
            return doGetObjectFromFactoryBean(factory,beanName);
        }
    }

    private Object doGetObjectFromFactoryBean(final  FactoryBean factory,final String beanName){
        try {
            return factory.getObject();
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }
}
