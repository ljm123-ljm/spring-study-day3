package com.ljm.context;

import java.util.EventObject;

/**
 * @Author jmle
 * @Date 2022/2/23 17:32
 * @Version 1.0
 */
public abstract class ApplicationEvent extends EventObject {
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ApplicationEvent(Object source) {
        super(source);
    }
}
