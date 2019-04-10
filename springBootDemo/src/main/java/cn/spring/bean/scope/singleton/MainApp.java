package cn.spring.bean.scope.singleton;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.dataModel.BeanDemo;

public class MainApp {
//  <bean id="helloWorld" class="cn.spring.bean.BeanDemo">
//输出
//  Your Message : Hello World!- by xml
//  Your Message : helloWorld!-by set

    private static final String beanXml_path = "spring/configFile/Beans.xml";//src目录下

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext(beanXml_path);
        BeanDemo obj = (BeanDemo) context.getBean("helloWorld");
        obj.getMessage();
        obj.setMessage("helloWorld!-by set"); 

        BeanDemo obj2 = (BeanDemo) context.getBean("helloWorld");
        obj2.getMessage();
    }

}