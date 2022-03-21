package com.ljm.bean;

import com.ljm.factory.FactoryBean;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author jmle
 * @Date 2022/2/22 18:45
 * @Version 1.0
 */
public class ProxyBeanFactory implements FactoryBean<IUSerDao> {
    @Override
    public IUSerDao getObject() throws Exception {
        InvocationHandler handler = (proxy, method, args) -> {

            // 添加排除方法
            if ("toString".equals(method.getName())) {
                return this.toString();
            }

            Map<String, String> hashMap = new HashMap<>();
            hashMap.put("10001", "小傅哥");
            hashMap.put("10002", "八杯水");
            hashMap.put("10003", "阿毛");

            return "你被代理了 " + method.getName() + "：" + hashMap.get(args[0].toString());
        };
        return (IUSerDao) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{IUSerDao.class}, handler);
    }

    @Override
    public Class<?> getObjectType() {
        return IUSerDao.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
