package com.ljm.bean;

import com.ljm.context.ApplicationContext;
import com.ljm.context.ApplicationContextAware;
import com.ljm.factory.*;

/**
 * @Author jmle
 * @Date 2022/2/10 19:00
 * @Version 1.0
 */
public class UserService implements BeanNameAware, BeanClassLoaderAware, ApplicationContextAware, BeanFactoryAware,InitializingBean, DisposableBean {
    private String uid;
    private IUSerDao userDao;
    private String company;
    private String location;
    private ApplicationContext applicationContext;
    private BeanFactory beanFactory;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String queryUserInfo() {
        return userDao.queryUserName(uid) + "," + company + "," + location;
    }

    public String getuId() {
        return uid;
    }

    public void setuId(String uId) {
        this.uid = uId;
    }

    public IUSerDao getUserDao() {
        return userDao;
    }

    public void setUserDao(IUSerDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("执行：UserService.destroy");
    }

    @Override
    public void afterPropertiesSet() {
        System.out.println("执行：UserService.afterPropertiesSet");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext=applicationContext;
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        System.out.println("ClassLoader:"+classLoader);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) {
         this.beanFactory=beanFactory;
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("Bean Name is：" + name);
    }
    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public BeanFactory getBeanFactory() {
        return beanFactory;
    }
}
