package cn.spring.aop.baseOnXml;

import cn.Util;

public class Logging {
   public void beforeAdvice(){
      Util.print("beforeAdvice");
   }
   public void afterAdvice(){
      Util.print("afterAdvice");
   }
   /** 
    * This is the method which I would like to execute
    * when any method returns.
    */
   public void afterReturningAdvice(Object retVal){
      Util.print("afterReturningAdvice:" + retVal.toString() );
   }
   /**
    * This is the method which I would like to execute
    * if there is an exception raised.
    */
   public void AfterThrowingAdvice(IllegalArgumentException ex){
      Util.print("AfterThrowingAdvice" + ex.toString());   
   }  
}