package com.ljm.Test;

import com.ljm.bean.event.CustomEvent;
import com.ljm.context.support.ClassPathXmlApplicationContext;
import org.junit.Test;

import java.io.IOException;

/**
 * @Author jmle
 * @Date 2022/2/23 18:32
 * @Version 1.0
 */
public class ApiTest9 {

    @Test
    public void test_event() throws IOException {
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        classPathXmlApplicationContext.publishEvent(new CustomEvent(classPathXmlApplicationContext,1000000L,"成功了"));
        classPathXmlApplicationContext.registerShutdownHook();
    }
}
