package cn.spring.annotations.qualifier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import cn.Util;

public class Profile {
   @Autowired
   @Qualifier("student2")
   private Student student;
   public Profile(){
      Util.print("Inside Profile constructor." );
   }
   public void printAge() {
      Util.print("Age : " + student.getAge() );
   }
   public void printName() {
      Util.print("Name : " + student.getName() );
   }
}