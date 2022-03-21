package com.ljm.aop;

import org.aopalliance.intercept.MethodInterceptor;

/**
 * @Author jmle
 * @Date 2022/2/25 14:07
 * @Version 1.0
 */
public class AdvisedSupport {


    private boolean proxyTargetClass=false;

    //被代理的目标对象
    private TargetSource targetSource;
    //方法拦截器
    private MethodInterceptor methodInterceptor;
    //方法匹配器
    private MethodMatcher methodMatcher;

    public TargetSource getTargetSource() {
        return targetSource;
    }

    public void setTargetSource(TargetSource targetSource) {
        this.targetSource = targetSource;
    }

    public MethodInterceptor getMethodInterceptor() {
        return methodInterceptor;
    }

    public void setMethodInterceptor(MethodInterceptor methodInterceptor) {
        this.methodInterceptor = methodInterceptor;
    }

    public MethodMatcher getMethodMatcher() {
        return methodMatcher;
    }

    public void setMethodMatcher(MethodMatcher methodMatcher) {
        this.methodMatcher = methodMatcher;
    }

    public void setProxyTargetClass(boolean proxyTargetClass) {
        this.proxyTargetClass = proxyTargetClass;
    }

    public boolean isProxyTargetClass(){
        return proxyTargetClass;
    }
}
