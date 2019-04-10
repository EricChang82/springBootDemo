package cn.spring.container.ApplicationContext.fileSystemXmlApplicationContext;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import cn.dataModel.BeanDemo;

public class MainApp {
    
    private static final String absolute_beanXml_path = "D:/newProject/WMS_V5_NEW/devProject/src/spring/configFile/Beans.xml";//src目录下

    public static void main(String[] args) {
        //        ApplicationContext context = new ClassPathXmlApplicationContext(beanXml_path);
        ApplicationContext context = new FileSystemXmlApplicationContext(absolute_beanXml_path);
        BeanDemo obj = (BeanDemo) context.getBean("helloWorld");
        obj.getMessage();
    }
    
    
    
    
    
    
    
    
    
    
}