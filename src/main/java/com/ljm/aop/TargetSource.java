package com.ljm.aop;

/**
 * @Author jmle
 * @Date 2022/2/25 14:05
 * @Version 1.0
 */
public class TargetSource {

    private final Object target;

    public TargetSource(Object target) {
        this.target = target;
    }

    public Class<?> [] getTargetClass(){
        return this.target.getClass().getInterfaces();
    }

    public Object getTarget(){
        return this.target;
    }


}
