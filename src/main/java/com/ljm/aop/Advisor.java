package com.ljm.aop;

import org.aopalliance.aop.Advice;

/**
 * @Author jmle
 * @Date 2022/3/2 15:10
 * @Version 1.0
 */
public interface Advisor {

    Advice getAdvice();
}
