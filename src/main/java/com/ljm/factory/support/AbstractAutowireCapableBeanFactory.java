package com.ljm.factory.support;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.ljm.factory.*;
import com.ljm.factory.config.AutowireCapableBeanFactory;
import com.ljm.factory.config.BeanDefinition;
import com.ljm.factory.config.BeanPostProcessor;
import com.ljm.factory.config.BeanReference;
import com.ljm.props.PropertyValue;
import com.ljm.props.PropertyValues;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Author jmle
 * @Date 2022/2/10 17:58
 * @Version 1.0
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory implements AutowireCapableBeanFactory {

    private InstantiationStrategy instantiationStrategy = new CglibSubclassingInstantiationStrategy();

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        Object bean;
        bean=resolveBeforeInstantiation(beanName,beanDefinition);
        if(bean!=null){
            return bean;
        }
        bean = createBeanInstance(beanDefinition, beanName, args);
        applyPropertyValues(beanName, bean, beanDefinition);
        bean = initializeBean(beanName, bean, beanDefinition);
        //注册实现了DisposableBean接口的Bean的对象
        registerDisposableBeanNecessary(beanName,bean,beanDefinition);
        //判断作用域
        if(beanDefinition.isSingleton()){
            addSingleton(beanName, bean);
        }
        return bean;
    }

    protected Object resolveBeforeInstantiation(String beanName,BeanDefinition beanDefinition){
        Object bean = applyBeanPostProcessorBeforeInitialization(beanDefinition.getBeanClass(), beanName);
        if(bean!=null){
            bean=applyBeanPostProcessAfterInitialization(bean,beanName);
        }
        return bean;
    }

    protected Object createBeanInstance(BeanDefinition beanDefinition, String beanName, Object[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Constructor constructor = null;
        Class<?> beanClass = beanDefinition.getBeanClass();
        Constructor<?>[] declaredConstructors = beanClass.getDeclaredConstructors();
        for (Constructor<?> declaredConstructor : declaredConstructors) {
            if (null != args && declaredConstructor.getParameterTypes().length == args.length) {
                constructor = declaredConstructor;
            }
            return getInstantiationStrategy().instantiate(beanDefinition, beanName, constructor, args);
        }
        return null;
    }

    public void applyPropertyValues(String beanName, Object bean, BeanDefinition beanDefinition) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        PropertyValues propertyValues = beanDefinition.getPropertyValues();
        for (PropertyValue propertyValue : propertyValues.getPropertyValues()) {
            String name = propertyValue.getName();
            Object value = propertyValue.getValue();
            if (value instanceof BeanReference) {
                BeanReference beanReference = (BeanReference) value;
                value = getBean(beanReference.getBeanName());
            }
            BeanUtil.setFieldValue(bean, name, value);
        }

    }

    public InstantiationStrategy getInstantiationStrategy() {
        return instantiationStrategy;
    }

    public void setInstantiationStrategy(InstantiationStrategy instantiationStrategy) {
        this.instantiationStrategy = instantiationStrategy;
    }

    @Override
    public Object applyBeanPostProcessAfterInitialization(Object existingBean, String beanName) {
        Object result = existingBean;
        for (BeanPostProcessor beanPostProcessor : getBeanPostProcessors()) {
            Object current = beanPostProcessor.postProcessAfterInitialization(result, beanName);
            if (current == null) {
                return result;
            }
            result = current;
        }
        return result;
    }

    @Override
    public Object applyBeanPostProcessorBeforeInitialization(Object existingBean, String beanName) {
        Object result = existingBean;
        for (BeanPostProcessor beanPostProcessor : getBeanPostProcessors()) {
            Object current = beanPostProcessor.postProcessBeforeInitialization(result, beanName);
            if (current == null) {
                return result;
            }
            result = current;
        }
        return result;
    }

    private Object initializeBean(String beanName, Object bean, BeanDefinition beanDefinition) {
        if(bean instanceof Aware){
            if(bean instanceof BeanFactoryAware){
                ((BeanFactoryAware) bean).setBeanFactory(this);
            }
            if(bean instanceof BeanClassLoaderAware){
                ((BeanClassLoaderAware) bean).setBeanClassLoader(getBeanClassLoader());
            }
            if(bean instanceof BeanNameAware){
                ((BeanNameAware) bean).setBeanName(beanName);
            }
        }
        Object wrappedBean = applyBeanPostProcessorBeforeInitialization(bean, beanName);
        invokeInitMethods(beanName, wrappedBean, beanDefinition);
        wrappedBean = applyBeanPostProcessAfterInitialization(wrappedBean, beanName);
        return wrappedBean;

    }

    private void invokeInitMethods(String beanName, Object wrappedBean, BeanDefinition beanDefinition) {
        if(wrappedBean instanceof InitializingBean){
            ((InitializingBean) wrappedBean).afterPropertiesSet();
        }
        try {
            String initMethodName = beanDefinition.getInitMethodName();
            if(StrUtil.isNotEmpty(initMethodName) && !(wrappedBean instanceof InitializingBean)){
                Method initMethod = beanDefinition.getBeanClass().getMethod(initMethodName);
                initMethod.invoke(wrappedBean);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void registerDisposableBeanNecessary(String beanName,Object bean,BeanDefinition beanDefinition){
        //非Singleton类型的Bean不执行销毁方法
        if(!beanDefinition.isSingleton()){
            return;
        }
        if(bean instanceof DisposableBean || StrUtil.isNotEmpty(beanDefinition.getDestroyMethodName())){
             registerDisposableBean(beanName,new DisposableBeanAdapter(bean,beanName,beanDefinition));
        }
    }

}
