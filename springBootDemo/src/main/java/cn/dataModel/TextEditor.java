package cn.dataModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
public class TextEditor {
    @Autowired
   private SpellChecker spellChecker3332;
 
    public SpellChecker getSpellChecker( ) {
        return spellChecker3332;
     }
   
  public void setSpellChecker(SpellChecker spellChecker1 ){
      this.spellChecker3332 = spellChecker1;
   }


   public void spellCheck() {
       System.out.println("1111111");
//      spellChecker3332.checkSpelling();
   }
   
}