package com.ljm.Test;

import com.ljm.bean.UserService;
import com.ljm.context.support.ClassPathXmlApplicationContext;
import org.junit.Test;

import java.io.IOException;

/**
 * @Author jmle
 * @Date 2022/2/17 17:57
 * @Version 1.0
 */
public class ApiTest7 {

    @Test
    public void test_xml() throws IOException {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        applicationContext.registerShutdownHook();
        UserService userService = applicationContext.getBean("userService", UserService.class);
        String result = userService.queryUserInfo();
        System.out.println("测试结果"+result);
        System.out.println("ApplicationContextAware："+userService.getApplicationContext());
        System.out.println("BeanFactoryAware："+userService.getBeanFactory());
    }
}
