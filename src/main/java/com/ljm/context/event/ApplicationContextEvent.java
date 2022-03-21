package com.ljm.context.event;

import com.ljm.context.ApplicationContext;
import com.ljm.context.ApplicationEvent;

/**
 * @Author jmle
 * @Date 2022/2/23 17:55
 * @Version 1.0
 */
public class ApplicationContextEvent extends ApplicationEvent {
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ApplicationContextEvent(Object source) {
        super(source);
    }

    public final ApplicationContext getApplicationContext(){
        return (ApplicationContext) getSource();
    }
}
