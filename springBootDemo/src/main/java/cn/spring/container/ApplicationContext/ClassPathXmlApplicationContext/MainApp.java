package cn.spring.container.ApplicationContext.ClassPathXmlApplicationContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.dataModel.BeanDemo;
import cn.spring.TestConstant;


public class MainApp {
    
//    private static final String beanXml_path = "spring/configFile/Beans.xml";//src目录下

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext(TestConstant.beanXml_path);
        BeanDemo obj = (BeanDemo) context.getBean("helloWorld");
        obj.getMessage();
    }
    
    
    
    
    
    
    
    
    
    
}