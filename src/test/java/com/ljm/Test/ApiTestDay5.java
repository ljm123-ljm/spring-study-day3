package com.ljm.Test;

import com.ljm.bean.UserService;
import com.ljm.context.support.ClassPathXmlApplicationContext;
import org.junit.Test;

import java.io.IOException;

/**
 * @Author jmle
 * @Date 2022/2/14 18:58
 * @Version 1.0
 */
public class ApiTestDay5 {

    @Test
    public void text_xml() throws IOException {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:springPostProcessor.xml");
        UserService userService = (UserService) applicationContext.getBean("userService", UserService.class);
        String result = userService.queryUserInfo();
        System.out.println("测试结果:"+result);
    }

}
