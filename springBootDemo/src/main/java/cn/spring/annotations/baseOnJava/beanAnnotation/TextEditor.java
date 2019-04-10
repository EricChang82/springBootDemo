package cn.spring.annotations.baseOnJava.beanAnnotation;
public class TextEditor {
   private SpellChecker spellChecker;  //不需要Autowired的注解了，也能获得对象。因为是通过config中配置,生成时指定的
   public TextEditor(SpellChecker spellChecker){
      System.out.println("Inside TextEditor constructor." );
      this.spellChecker = spellChecker;
   }
   public void spellCheck(){
      spellChecker.checkSpelling();
   }
}