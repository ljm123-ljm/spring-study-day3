package com.ljm.bean.event;

import com.ljm.context.ApplicationListener;
import com.ljm.context.event.ContextRefreshedEvent;

/**
 * @Author jmle
 * @Date 2022/2/23 18:29
 * @Version 1.0
 */
public class ContextRefreshedEventListener implements ApplicationListener<ContextRefreshedEvent> {
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println("刷新事件"+this.getClass().getName());
    }
}
