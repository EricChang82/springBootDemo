package cn.dataModel;

import org.springframework.stereotype.Component;

import cn.Util;

@Component
public class SpellChecker {
   public SpellChecker(){
      Util.print("Inside SpellChecker constructor." );
   }
   public void checkSpelling(){
      Util.print("Inside checkSpelling." );
   }  
}