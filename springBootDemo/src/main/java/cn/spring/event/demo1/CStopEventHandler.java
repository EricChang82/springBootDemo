package cn.spring.event.demo1;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextStoppedEvent;

import cn.Util;

public class CStopEventHandler implements ApplicationListener<ContextStoppedEvent> {
    public void onApplicationEvent(ContextStoppedEvent event) {
        Util.print("ContextStoppedEvent Received");
    }
}