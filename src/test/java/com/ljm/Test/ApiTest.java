package com.ljm.Test;

import com.ljm.bean.UserDao;
import com.ljm.bean.UserService;
import com.ljm.factory.config.BeanDefinition;
import com.ljm.factory.config.BeanReference;
import com.ljm.factory.support.DefaultListableBeanFactory;
import com.ljm.props.PropertyValue;
import com.ljm.props.PropertyValues;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;

/**
 * @Author jmle
 * @Date 2022/2/10 19:03
 * @Version 1.0
 */
public class ApiTest {
    @Test
    public void test_BeanFactory() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        DefaultListableBeanFactory defaultListableBeanFactory = new DefaultListableBeanFactory();
        defaultListableBeanFactory.registerBeanDefinition("userDao",new BeanDefinition(UserDao.class));
        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("uid","10001"));
        propertyValues.addPropertyValue(new PropertyValue("userDao",new BeanReference("userDao")));
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class, propertyValues);
        defaultListableBeanFactory.registerBeanDefinition("userService",beanDefinition);
        UserService userService = (UserService) defaultListableBeanFactory.getBean("userService");
        userService.queryUserInfo();
    }
}
