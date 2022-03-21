package com.ljm.aop;

/**
 * @Author jmle
 * @Date 2022/3/2 15:13
 * @Version 1.0
 */
public interface PointcutAdvisor extends Advisor {

    Pointcut getPointcut();
}
