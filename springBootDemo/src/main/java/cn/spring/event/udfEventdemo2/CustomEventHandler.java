package cn.spring.event.udfEventdemo2;

import org.springframework.context.ApplicationListener;

import cn.Util;

public class CustomEventHandler implements ApplicationListener<CustomEvent> {
    public void onApplicationEvent(CustomEvent event) {
        Util.print(event.exeEventBiz());
    }
}