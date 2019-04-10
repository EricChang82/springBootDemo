package cn.spring.annotations.baseOnJava.beanAnnotation;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainApp {
    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(TextEditorConfig.class);//根据配置生成bean对象
        TextEditor te = ctx.getBean(TextEditor.class); //获取bean对象
        te.spellCheck();
    }
}