package com.ljm.factory.support;

import com.ljm.factory.FactoryBean;
import com.ljm.factory.config.BeanDefinition;
import com.ljm.factory.config.BeanPostProcessor;
import com.ljm.factory.config.ConfigurableBeanFactory;
import com.ljm.util.ClassUtil;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author jmle
 * @Date 2022/2/10 17:51
 * @Version 1.0
 */
public abstract class AbstractBeanFactory extends FactoryBeanRegistrySupport implements ConfigurableBeanFactory {

    private ClassLoader beanClassLoader= ClassUtil.getDefaultClassLoader();

    private final List<BeanPostProcessor> beanPostProcessorList = new ArrayList<>();

    @Override
    public Object getBean(String name) {
        return doGetBean(name, null);
    }

    @Override
    public Object getBean(String name, Object... args) {
        return doGetBean(name, args);
    }

    @Override
    public <T> T getBean(String name, Class<T> requiredType) {
        return (T) getBean(name);
    }

    protected <T> T doGetBean(final String name, final Object[] args) {
        Object sharedInstance = getSingleton(name);
        if (sharedInstance != null) {
            return (T) getObjectFromBeanInstance(sharedInstance,name);
        }
        BeanDefinition beanDefinition = getBeanDefinition(name);
        try {
            Object bean = createBean(name, beanDefinition, args);
            return (T) getObjectFromBeanInstance(bean,name);
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Object getObjectFromBeanInstance(Object beanInstance,String beanName){
        if(! (beanInstance instanceof FactoryBean)){
            return beanInstance;
        }
        Object object = getCacheObjectForFactoryBean(beanName);
        if(object==null){
            FactoryBean<?> factoryBean= (FactoryBean<?>) beanInstance;
            object=getObjectFromFactoryBean(factoryBean,beanName);
        }
        return object;
    }

    protected abstract BeanDefinition getBeanDefinition(String beanName);

    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException;

    @Override
    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor) {
        this.beanPostProcessorList.remove(beanPostProcessor);
        this.beanPostProcessorList.add(beanPostProcessor);
    }

    public List<BeanPostProcessor> getBeanPostProcessors() {
        return this.beanPostProcessorList;
    }

    public ClassLoader getBeanClassLoader(){
        return this.beanClassLoader;
    }
}
