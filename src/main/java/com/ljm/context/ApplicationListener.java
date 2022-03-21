package com.ljm.context;

import java.util.EventListener;

/**
 * @Author jmle
 * @Date 2022/2/23 17:34
 * @Version 1.0
 */
public interface ApplicationListener<E extends ApplicationEvent> extends EventListener {

    void onApplicationEvent(E event);
}
