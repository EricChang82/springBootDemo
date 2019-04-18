package cn.spring.annotations.JSR25;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import cn.Util;

public class HelloWorld {
   private String message;
   public void setMessage(String message){
      this.message  = message;
   }
   public String getMessage(){
      Util.print("Your Message : " + message);
      return message;
   }
   @PostConstruct
   public void init(){
      Util.print("Bean is going through init.");
   }
   @PreDestroy
   public void destroy(){
      Util.print("Bean will destroy now.");
   }
}