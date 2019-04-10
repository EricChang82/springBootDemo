package cn.spring.event.udfEventdemo2;

import org.springframework.context.ApplicationListener;

public class CustomEventHandler2 implements ApplicationListener<CustomEvent2> {
    
    public void onApplicationEvent(CustomEvent2 event) {
        System.out.println(event.exeEventBiz());
    }
}