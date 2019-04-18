package cn.spring.aop.baseOnXml;

import cn.Util;

public class Student {
   private Integer age;
   private String name;
   public void setAge(Integer age) {
      this.age = age;
   }
   public Integer getAge() {
      Util.print("Age : " + age );
      return age;
   }
   public void setName(String name) {
      this.name = name;
   }
   public String getName() {
      Util.print("Name : " + name );
      return name;
   }  
   public void printThrowException(){
       Util.print("Exception raised");
       throw new IllegalArgumentException();
   }
}