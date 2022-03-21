package com.ljm.context;

/**
 * @Author jmle
 * @Date 2022/2/23 17:33
 * @Version 1.0
 */
public interface ApplicationEventPublisher {

    void publishEvent(ApplicationEvent event);
}
