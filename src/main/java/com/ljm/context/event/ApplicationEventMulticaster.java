package com.ljm.context.event;

import com.ljm.context.ApplicationEvent;
import com.ljm.context.ApplicationListener;

/**
 * @Author jmle
 * @Date 2022/2/23 17:36
 * @Version 1.0
 */
public interface ApplicationEventMulticaster {

    void addApplicationListener(ApplicationListener<?> listener);



    void removeApplicationListener(ApplicationListener<?> listener);


    void multicastEvent(ApplicationEvent event);
}
