package cn.spring.annotations;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.dataModel.TextEditor;

public class MainApp {
//  <bean id="helloWorld" class="cn.spring.bean.BeanDemo">
//输出
//  Your Message : Hello World!- by xml
//  Your Message : helloWorld!-by set

    private static final String beanXml_path = "spring/configFile/Beans.xml";//src目录下

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext(beanXml_path);
//        BeanDemo obj = (BeanDemo) context.getBean("helloWorld");
//        obj.getMessage();
        
        TextEditor te = (TextEditor) context.getBean("textEditor");
        te.spellCheck();

    }

}