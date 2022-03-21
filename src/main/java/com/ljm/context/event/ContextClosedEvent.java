package com.ljm.context.event;

import com.ljm.context.ApplicationContext;

/**
 * @Author jmle
 * @Date 2022/2/23 17:57
 * @Version 1.0
 */
public class ContextClosedEvent extends ApplicationContextEvent {
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ContextClosedEvent(Object source) {
        super(source);
    }
}
