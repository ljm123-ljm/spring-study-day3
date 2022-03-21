package com.ljm.context.support;

import com.ljm.context.ApplicationEvent;
import com.ljm.context.ApplicationListener;
import com.ljm.context.ConfigurableApplicationContext;
import com.ljm.context.event.ApplicationEventMulticaster;
import com.ljm.context.event.ContextClosedEvent;
import com.ljm.context.event.ContextRefreshedEvent;
import com.ljm.context.event.SimpleApplicationEventMulticaster;
import com.ljm.factory.ConfigurableListableBeanFactory;
import com.ljm.factory.config.BeanFactoryPostProcess;
import com.ljm.factory.config.BeanPostProcessor;
import com.ljm.io.DefaultResourceLoader;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;

/**
 * @Author jmle
 * @Date 2022/2/14 17:51
 * @Version 1.0
 */
public abstract class AbstractApplicationContext extends DefaultResourceLoader implements ConfigurableApplicationContext {

    public static final String APPLICATION_EVENT_MULTICASTER_BEAN_NAME = "applicationEventMulticaster";

    private ApplicationEventMulticaster applicationEventMulticaster;
    @Override
    public void refresh() throws IOException {
        // 1.创建BeanFactory,并加载BeanDefinition
         refreshBeanFactory();
        //2.获取BeanFactory
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();
        //3.添加ApplicationContextAwareProcessor,让继承自ApplicationContextAware的Bean对象都能感知所属的ApplicationContext
        beanFactory.addBeanPostProcessor(new ApplicationContextAwareProcessor(this));
        //4.在Bean实例化之前,执行BeanFactoryPostProcess
        invokeBeanFactoryPostProcessors(beanFactory);
        //5.BeanPostProcess 需要提前于其他Bean对象实例化之前的注册操作
        registerBeanPostProcessors(beanFactory);
        //6.初始化事件发布者
        initApplicationEventMulticaster();
        //7.注册事件监听器
        registerListeners();
        //8.提前实例化单例Bean对象
        beanFactory.preInstantiateSingletons();
        //9.发布容器刷新完成事件
        finishRefresh();
    }

    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) {
        return getBeanFactory().getBeansOfType(type);
    }

    @Override
    public String[] getBeanDefinitionNames() {
        return getBeanFactory().getBeanDefinitionNames();
    }

    @Override
    public Object getBean(String name) {
        return getBeanFactory().getBean(name);
    }

    @Override
    public Object getBean(String name, Object... args) {
        return getBeanFactory().getBean(name, args);
    }
    @Override
    public  <T> T getBean(String name, Class<T> requiredType) {
        return getBeanFactory().getBean(name, requiredType);
    }

    private void invokeBeanFactoryPostProcessors(ConfigurableListableBeanFactory beanFactory) throws IOException {
        Map<String, BeanFactoryPostProcess> beanFactoryPostProcessorMap = beanFactory.getBeansOfType(BeanFactoryPostProcess.class);
        for (BeanFactoryPostProcess beanFactoryPostProcess : beanFactoryPostProcessorMap.values()) {
            beanFactoryPostProcess.postProcessBeanFactory(beanFactory);
        }
    }

    private void registerBeanPostProcessors(ConfigurableListableBeanFactory beanFactory) {
        Map<String, BeanPostProcessor> beanPostProcessorMap = beanFactory.getBeansOfType(BeanPostProcessor.class);
        for (BeanPostProcessor beanPostProcessor : beanPostProcessorMap.values()) {
            beanFactory.addBeanPostProcessor(beanPostProcessor);
        }
    }

    private void initApplicationEventMulticaster(){
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();
        applicationEventMulticaster= new SimpleApplicationEventMulticaster(beanFactory);
        beanFactory.registerSingleton(APPLICATION_EVENT_MULTICASTER_BEAN_NAME,applicationEventMulticaster);
    }

    private void registerListeners(){
        Collection<ApplicationListener> applicationListeners = getBeansOfType(ApplicationListener.class).values();
        for (ApplicationListener applicationListener : applicationListeners) {
            applicationEventMulticaster.addApplicationListener(applicationListener);
        }
    }

    private void finishRefresh(){
        publishEvent(new ContextRefreshedEvent(this));
    }

    @Override
    public void publishEvent(ApplicationEvent event) {
        applicationEventMulticaster.multicastEvent(event);
    }

    /**
     * 刷新容器
     */
    protected abstract void refreshBeanFactory();

    /**
     * 获得工厂类型
     */
    protected abstract ConfigurableListableBeanFactory getBeanFactory();

    /**
     * 注册钩子
     */
    @Override
    public void registerShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(this::close));

    }

    /**
     * 销毁实例
     *
     */
    @Override
    public void close() {
        //发布容器关闭事件
        publishEvent(new ContextClosedEvent(this));
        //执行销毁单例bean的方法
        getBeanFactory().destroySingletons();
    }
}
