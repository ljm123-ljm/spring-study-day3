package com.ljm.context.event;

import com.ljm.context.ApplicationEvent;
import com.ljm.context.ApplicationListener;
import com.ljm.factory.BeanFactory;

/**
 * @Author jmle
 * @Date 2022/2/23 17:58
 * @Version 1.0
 */
public class SimpleApplicationEventMulticaster extends AbstractApplicationEventMulticaster {

    public SimpleApplicationEventMulticaster(BeanFactory beanFactory){
        setBeanFactory(beanFactory);
    }
    @Override
    public void multicastEvent(final ApplicationEvent event) {
        for (final ApplicationListener listener : getApplicationListeners(event)) {
            listener.onApplicationEvent(event);
        }
    }
}
