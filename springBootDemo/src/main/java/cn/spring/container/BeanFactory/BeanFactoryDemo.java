
package cn.spring.container.BeanFactory;

import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
/**
 * Project Name:devProject
 * @author changle
 * Purpose:
 * Create Time: 2019年2月28日 
 * Create Specification:
 * Modified Time:
 * Modified by:
 * Modified Specification:
 * Version: 1.0
 */

import cn.dataModel.BeanDemo;

public class BeanFactoryDemo {
    private static final String beanXml_path = "spring/configFile/Beans.xml";//src目录下
    
    public static void main(String[] args) {
        XmlBeanFactory factory = new XmlBeanFactory(new ClassPathResource(beanXml_path));
        BeanDemo beanObj = (BeanDemo) factory.getBean("helloWorld");
        beanObj.getMessage();
    }
    

}
