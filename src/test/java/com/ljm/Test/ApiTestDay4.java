package com.ljm.Test;

import cn.hutool.core.io.IoUtil;
import com.ljm.bean.UserService;
import com.ljm.factory.support.DefaultListableBeanFactory;
import com.ljm.io.DefaultResourceLoader;
import com.ljm.io.Resource;
import com.ljm.xml.XmlBeanDefinitionReader;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;

/**
 * @Author jmle
 * @Date 2022/2/11 18:24
 * @Version 1.0
 */
public class ApiTestDay4 {
    private DefaultResourceLoader resourceLoader;
    @Test
    public void test(){

    }
    @Before
    public void init(){
        resourceLoader=new DefaultResourceLoader();
    }
    @Test
    public void test_classpath() throws IOException {
        Resource resource = resourceLoader.getResource("classpath:important.properties");
        InputStream inputStream = resource.getInputStream();
        String content = IoUtil.readUtf8(inputStream);
        System.out.println(content);
    }
    @Test
    public void test_xml()  {
        DefaultListableBeanFactory defaultListableBeanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(defaultListableBeanFactory);
        xmlBeanDefinitionReader.loadBeanDefinitions("classpath:spring.xml");
        UserService userService = (UserService) defaultListableBeanFactory.getBean("userService", UserService.class);
        String result=userService.queryUserInfo();
        System.out.println(result);
    }
}
