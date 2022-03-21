package com.ljm.Test;

import com.ljm.bean.UserService;
import com.ljm.context.support.ClassPathXmlApplicationContext;
import org.junit.Test;

import java.io.IOException;

/**
 * @Author jmle
 * @Date 2022/2/15 17:35
 * @Version 1.0
 */
public class ApiTest6 {

    @Test
    public void test_xml() throws IOException {
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        classPathXmlApplicationContext.registerShutdownHook();
        UserService userService = (UserService) classPathXmlApplicationContext.getBean("userService", UserService.class);
        String result = userService.queryUserInfo();
        System.out.println("测试结果：" + result);
    }
    @Test
    public void test_hook(){
        Runtime.getRuntime().addShutdownHook(new Thread(() -> System.out.println("close！")));
    }
}
