package cn.spring.event.demo1;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextStartedEvent;

import cn.Util;

public class CStartEventHandler implements ApplicationListener<ContextStartedEvent> {
    public void onApplicationEvent(ContextStartedEvent event) {
        Util.print("ContextStartedEvent Received");
    }
}