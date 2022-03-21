package com.ljm.bean.event;

import com.ljm.context.ApplicationListener;

import java.util.Date;

/**
 * @Author jmle
 * @Date 2022/2/23 18:30
 * @Version 1.0
 */
public class CustomEventListener implements ApplicationListener<CustomEvent> {
    @Override
    public void onApplicationEvent(CustomEvent event) {
        System.out.println("收到:"+event.getSource()+"消息;时间"+new Date());
        System.out.println("消息:"+event.getId()+":"+event.getMessage());
    }
}
