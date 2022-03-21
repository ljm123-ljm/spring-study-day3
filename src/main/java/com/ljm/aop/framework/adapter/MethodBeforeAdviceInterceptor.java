package com.ljm.aop.framework.adapter;

import com.ljm.aop.MethodBeforeAdvice;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * @Author jmle
 * @Date 2022/3/2 15:19
 * @Version 1.0
 */
public class MethodBeforeAdviceInterceptor implements MethodInterceptor {

    private MethodBeforeAdvice advice;

    public MethodBeforeAdviceInterceptor(MethodBeforeAdvice advice) {
        this.advice = advice;
    }

    public MethodBeforeAdviceInterceptor() {
    }

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        this.advice.before(invocation.getMethod(),invocation.getArguments(),invocation.getThis() );
        return invocation.proceed();
    }
}
