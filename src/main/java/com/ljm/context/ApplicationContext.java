package com.ljm.context;

import com.ljm.factory.HierarchicalBeanFactory;
import com.ljm.factory.ListableBeanFactory;
import com.ljm.io.ResourceLoader;

/**
 * @Author jmle
 * @Date 2022/2/14 17:46
 * @Version 1.0
 */
public interface ApplicationContext extends ListableBeanFactory, HierarchicalBeanFactory, ResourceLoader,ApplicationEventPublisher {
}
