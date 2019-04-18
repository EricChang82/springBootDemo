package cn.dataModel;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Component;

import cn.Util;

@Component
public class BeanDemo {
   private String message;
   @Required
   public void setMessage(String message){
      this.message  = message;
   }
   public String getMessage(){
      Util.print("bean中：Your Message : " + message);
      return message;
   }
   
   public void init(){
       Util.print("bean中：执行bean的init");
    }
    public void destroy(){
       Util.print("bean中：执行bean的destory");
    }
}