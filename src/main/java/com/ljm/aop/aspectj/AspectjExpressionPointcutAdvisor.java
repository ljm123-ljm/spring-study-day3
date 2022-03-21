package com.ljm.aop.aspectj;

import com.ljm.aop.Pointcut;
import com.ljm.aop.PointcutAdvisor;
import org.aopalliance.aop.Advice;

/**
 * @Author jmle
 * @Date 2022/3/2 15:45
 * @Version 1.0
 */
public class AspectjExpressionPointcutAdvisor implements PointcutAdvisor {

    private AspectjExpressionPointcut pointcut;

    private  Advice advice;

    private String expression;

    public void setExpression(String expression) {
        this.expression = expression;
    }

    @Override
    public Pointcut getPointcut() {
        if(null==pointcut){
            pointcut=new AspectjExpressionPointcut(expression);
        }
        return pointcut;
    }

    @Override
    public Advice getAdvice() {
        return advice;
    }

    public void setAdvice(Advice advice) {
        this.advice = advice;
    }
}
