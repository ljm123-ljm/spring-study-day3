package com.ljm.Test;

import com.ljm.aop.AdvisedSupport;
import com.ljm.aop.MethodMatcher;
import com.ljm.aop.TargetSource;
import com.ljm.aop.aspectj.AspectjExpressionPointcut;
import com.ljm.aop.framework.Cglib2AopProxy;
import com.ljm.aop.framework.JdkDynamicAopProxy;
import com.ljm.aop.framework.ReflectiveMethodInvocation;
import com.ljm.bean.IUserService;
import com.ljm.bean.UserService2;
import com.ljm.bean.UserServiceInterceptor;
import org.aopalliance.intercept.MethodInterceptor;
import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Author jmle
 * @Date 2022/2/25 14:55
 * @Version 1.0
 */
public class ApiTest10 {

    @Test
    public void test_aop() throws NoSuchMethodException {
        AspectjExpressionPointcut pointcut = new AspectjExpressionPointcut("execution(* com.ljm.bean.UserService2.*(..))");
        Class<UserService2> clazz = UserService2.class;
        Method method = clazz.getDeclaredMethod("queryUserInfo");
        System.out.println(pointcut.matches(clazz));
        System.out.println(pointcut.matches(method,clazz));
    }

    @Test
    public void test_dynamic(){
        UserService2 userService = new UserService2();
        AdvisedSupport advisedSupport = new AdvisedSupport();
        advisedSupport.setTargetSource(new TargetSource(userService));
        advisedSupport.setMethodInterceptor(new UserServiceInterceptor());
        advisedSupport.setMethodMatcher(new AspectjExpressionPointcut("execution(* com.ljm.bean.IUserService.*(..))"));
        IUserService proxy_jdk = new JdkDynamicAopProxy(advisedSupport).getProxy();
        System.out.println("测试结果:" +proxy_jdk.queryUserInfo());
        IUserService proxy_cglib = new Cglib2AopProxy(advisedSupport).getProxy();
        System.out.println("测试结果:"+proxy_cglib.register("小乐"));
    }

    @Test
    public void test_proxy_class(){
       IUserService userService = (IUserService) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{IUserService.class}, ((proxy, method, args) -> "你被代理了"));
        String result = userService.queryUserInfo();
        System.out.println("测试结果:"+result);
    }

    @Test
    public void test_proxy_method(){
        UserService2 targetObject = new UserService2();
        IUserService proxy = (IUserService) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), targetObject.getClass().getInterfaces(), new InvocationHandler() {
            //方法适配器
            MethodMatcher methodMatcher = new AspectjExpressionPointcut("execution(* com.ljm.bean.IUserService.*(..))");

            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if (methodMatcher.matches(method, targetObject.getClass())) {
                    MethodInterceptor methodInterceptor = invocation -> {
                        long start = System.currentTimeMillis();
                        try {
                            return invocation.proceed();
                        } finally {
                            System.out.println("监控 - Begin By AOP");
                            System.out.println("方法名称：" + invocation.getMethod().getName());
                            System.out.println("方法耗时：" + (System.currentTimeMillis() - start) + "ms");
                            System.out.println("监控 - End\r\n");
                        }
                    };
                    return methodInterceptor.invoke(new ReflectiveMethodInvocation(targetObject, method, args));
                }
                return method.invoke(targetObject, args);
            }
        });
        String result = proxy.queryUserInfo();
        System.out.println("测试结果：" + result);
    }
}
