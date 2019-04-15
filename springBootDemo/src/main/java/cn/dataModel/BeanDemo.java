package cn.dataModel;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Component;

@Component
public class BeanDemo {
   private String message;
   @Required
   public void setMessage(String message){
      this.message  = message;
   }
   public String getMessage(){
      System.out.println("bean中：Your Message : " + message);
      return message;
   }
   
   public void init(){
       System.out.println("bean中：执行bean的init");
    }
    public void destroy(){
       System.out.println("bean中：执行bean的destory");
    }
}