package com.ljm.Test;

import com.ljm.bean.UserService;
import com.ljm.context.support.ClassPathXmlApplicationContext;
import org.junit.Test;
import org.openjdk.jol.info.ClassLayout;

import java.io.IOException;

/**
 * @Author jmle
 * @Date 2022/2/22 18:50
 * @Version 1.0
 */
public class ApiTest8 {
    @Test
    public void test_prototype() throws IOException {
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        classPathXmlApplicationContext.registerShutdownHook();
        UserService userService = classPathXmlApplicationContext.getBean("userService", UserService.class);
        UserService userService1 = classPathXmlApplicationContext.getBean("userService", UserService.class);
        System.out.println(userService);
        System.out.println(userService1);
        System.out.println(userService + " 十六进制哈希：" + Integer.toHexString(userService.hashCode()));
        System.out.println(ClassLayout.parseInstance(userService).toPrintable());
    }

    @Test
    public void test_factory_bean() throws IOException {
        // 1.初始化 BeanFactory
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        applicationContext.registerShutdownHook();
        // 2. 调用代理方法
        UserService userService = applicationContext.getBean("userService", UserService.class);
        System.out.println("测试结果：" + userService.queryUserInfo());
    }

}
