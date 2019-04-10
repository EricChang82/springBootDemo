package cn.spring.event.udfEventdemo2;

import org.springframework.context.ApplicationEvent;

public class CustomEvent extends ApplicationEvent{   //必须继承ApplicationEvent
    
   public CustomEvent(Object source) {
      super(source);  //必须实现ApplicationEvent的构造函数
   }
   public String exeEventBiz(){
      return "My Custom Event";
   }
}