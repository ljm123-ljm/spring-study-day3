package com.ljm.context.annotation;

import cn.hutool.core.util.StrUtil;
import com.ljm.factory.config.BeanDefinition;
import com.ljm.factory.support.BeanDefinitionRegistry;
import com.ljm.stereotype.Component;

import java.lang.annotation.Annotation;
import java.util.Set;

/**
 * @Author jmle
 * @Date 2022/3/2 15:32
 * @Version 1.0
 */
public class ClassPathBeanDefinitionScanner extends ClassPathScanningCandidateComponentProvider {

    private BeanDefinitionRegistry registry;

    public ClassPathBeanDefinitionScanner(BeanDefinitionRegistry registry) {
        this.registry = registry;
    }

    public void doScan(String ... basePackages){
        for (String basePackage : basePackages) {
            Set<BeanDefinition> candidates = findCandidateComponents(basePackage);
            for (BeanDefinition beanDefinition : candidates) {
                String beanScope = resolveBeanScope(beanDefinition);
                if(StrUtil.isNotEmpty(beanScope)){
                    beanDefinition.setScope(beanScope);
                }
                registry.registerBeanDefinition(determineBeanName(beanDefinition),beanDefinition);
            }
        }
    }

    private String resolveBeanScope(BeanDefinition beanDefinition){
        Class beanClass = beanDefinition.getBeanClass();
        Scope  scope = (Scope) beanClass.getAnnotation(Scope.class);
        if(null!=scope){
            return scope.value();
        }
        return StrUtil.EMPTY;
    }

    private String determineBeanName(BeanDefinition beanDefinition){
        Class beanClass = beanDefinition.getBeanClass();
        Component component= (Component) beanClass.getAnnotation(Component.class);
        String value = component.value();
        if(StrUtil.isEmpty(value)){
            value=StrUtil.lowerFirst(beanClass.getSimpleName());
        }
        return value;
    }
}
