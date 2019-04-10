package cn.spring.aop.baseOnXml;
public class Logging {
   public void beforeAdvice(){
      System.out.println("beforeAdvice");
   }
   public void afterAdvice(){
      System.out.println("afterAdvice");
   }
   /** 
    * This is the method which I would like to execute
    * when any method returns.
    */
   public void afterReturningAdvice(Object retVal){
      System.out.println("afterReturningAdvice:" + retVal.toString() );
   }
   /**
    * This is the method which I would like to execute
    * if there is an exception raised.
    */
   public void AfterThrowingAdvice(IllegalArgumentException ex){
      System.out.println("AfterThrowingAdvice" + ex.toString());   
   }  
}