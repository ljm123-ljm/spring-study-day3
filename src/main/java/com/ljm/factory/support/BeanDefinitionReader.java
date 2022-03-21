package com.ljm.factory.support;


import com.ljm.io.Resource;
import com.ljm.io.ResourceLoader;

import java.io.IOException;

/**
 * @Author jmle
 * @Date 2022/2/11 17:10
 * @Version 1.0
 */
public interface BeanDefinitionReader {
    BeanDefinitionRegistry getRegistry();

    ResourceLoader getResourceLoader();

    void loadBeanDefinitions(Resource resource) throws IOException;

    void loadBeanDefinitions(Resource... resources);

    void loadBeanDefinitions(String locations);

    void loadBeanDefinitions(String... locations);

}
