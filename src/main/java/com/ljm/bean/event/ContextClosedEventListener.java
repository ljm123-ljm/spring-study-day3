package com.ljm.bean.event;

import com.ljm.context.ApplicationListener;
import com.ljm.context.event.ContextClosedEvent;

/**
 * @Author jmle
 * @Date 2022/2/23 18:27
 * @Version 1.0
 */
public class ContextClosedEventListener implements ApplicationListener<ContextClosedEvent> {
    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        System.out.println("关闭事件:"+this.getClass().getName());
    }
}
