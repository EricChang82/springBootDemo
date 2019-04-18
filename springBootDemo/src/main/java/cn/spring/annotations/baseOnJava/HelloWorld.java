package cn.spring.annotations.baseOnJava;

import cn.Util;

public class HelloWorld {
   private String message;

   public void setMessage(String message){
      this.message  = message;
   }

   public void getMessage(){
      Util.print("Your Message : " + message);
   }
}