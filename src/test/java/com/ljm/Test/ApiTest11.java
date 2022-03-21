package com.ljm.Test;

import com.ljm.aop.AdvisedSupport;
import com.ljm.aop.ClassFilter;
import com.ljm.aop.TargetSource;
import com.ljm.aop.aspectj.AspectjExpressionPointcut;
import com.ljm.aop.aspectj.AspectjExpressionPointcutAdvisor;
import com.ljm.aop.framework.ProxyFactory;
import com.ljm.aop.framework.adapter.MethodBeforeAdviceInterceptor;
import com.ljm.bean.*;
import com.ljm.context.support.ClassPathXmlApplicationContext;
import org.aopalliance.intercept.MethodInterceptor;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * @Author jmle
 * @Date 2022/3/2 16:06
 * @Version 1.0
 */
public class ApiTest11 {
    private AdvisedSupport advisedSupport;

    @Before
    public void init(){
        UserService3 userService3 = new UserService3();
        advisedSupport=new AdvisedSupport();
        advisedSupport.setTargetSource(new TargetSource(userService3));
        advisedSupport.setMethodInterceptor(new UserServiceInterceptor());
        advisedSupport.setMethodMatcher(new AspectjExpressionPointcut("execution(* com.ljm.bean.IUserService.*(..))"));
    }

    @Test
    public void test_proxyFactory(){
        advisedSupport.setProxyTargetClass(false);
        IUserService proxy = (IUserService) new ProxyFactory(advisedSupport).getProxy();
        System.out.println("测试结果:"+ proxy.queryUserInfo());
    }

    @Test
    public  void test_beforeAdvice(){
        UserServiceBeforeAdvice beforeAdvice = new UserServiceBeforeAdvice();
        MethodBeforeAdviceInterceptor interceptor = new MethodBeforeAdviceInterceptor(beforeAdvice);
        advisedSupport.setMethodInterceptor(interceptor);
        IUserService proxy = (IUserService) new ProxyFactory(advisedSupport).getProxy();
        System.out.println("测试结果:"+proxy.queryUserInfo());
    }

    @Test
    public void test_advisor(){
        IUserService userService=new UserService3();
        AspectjExpressionPointcutAdvisor advisor = new AspectjExpressionPointcutAdvisor();
        advisor.setExpression("execution(* com.ljm.bean.IUserService.*(..))");
        advisor.setAdvice(new MethodBeforeAdviceInterceptor(new UserServiceBeforeAdvice()));
        ClassFilter classFilter = advisor.getPointcut().getClassFilter();
        if(classFilter.matches(userService.getClass())){
            AdvisedSupport advisedSupport = new AdvisedSupport();
            TargetSource targetSource = new TargetSource(userService);
            advisedSupport.setTargetSource(targetSource);
            advisedSupport.setMethodInterceptor((MethodInterceptor) advisor.getAdvice());
            advisedSupport.setMethodMatcher(advisor.getPointcut().getMethodMatcher());
            advisedSupport.setProxyTargetClass(true);
            IUserService proxy = (IUserService) new ProxyFactory(advisedSupport).getProxy();
            System.out.println("测试结果:"+proxy.queryUserInfo());
        }
    }

    @Test
    public  void test_aop() throws IOException {
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        IUserService proxy = classPathXmlApplicationContext.getBean("userService", IUserService.class);
        System.out.println("测试结果:"+proxy.queryUserInfo());
    }


}
