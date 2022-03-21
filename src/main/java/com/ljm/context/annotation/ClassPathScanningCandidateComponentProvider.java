package com.ljm.context.annotation;

import cn.hutool.core.util.ClassUtil;
import com.ljm.factory.config.BeanDefinition;
import com.ljm.stereotype.Component;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @Author jmle
 * @Date 2022/3/2 15:26
 * @Version 1.0
 */
public class ClassPathScanningCandidateComponentProvider {

    public Set<BeanDefinition> findCandidateComponents(String basePackage){
        Set<BeanDefinition> candidates=new LinkedHashSet<>();
        Set<Class<?>> classes = ClassUtil.scanPackageByAnnotation(basePackage, Component.class);
        for (Class<?> clazz : classes) {
            candidates.add(new BeanDefinition(clazz));
        }
        return candidates;
    }
}
