package cn.spring.annotations.baseOnJava.beanAnnotation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TextEditorConfig {
    @Bean
    public TextEditor textEditor() {
        return new TextEditor(spellChecker()); //此处指定参数来源
    }

    @Bean
    public SpellChecker spellChecker() {
        return new SpellChecker();
    }
}