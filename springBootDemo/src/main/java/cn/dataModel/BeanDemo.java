package cn.dataModel;

import org.springframework.stereotype.Component;

import cn.Util;
import lombok.Getter;
import lombok.Setter;

@Component
@Setter@Getter
public class BeanDemo {
   private String message;
//   @Required
//   public void setMessage(String message){
//      this.message  = message;
//   }
//   public String getMessage(){
//      Util.print("bean中：Your Message : " + message);
//      return message;
//   }
   
   public void init(){
       Util.print("bean中：执行bean的init");
    }
    public void destroy(){
       Util.print("bean中：执行bean的destory");
    }
}