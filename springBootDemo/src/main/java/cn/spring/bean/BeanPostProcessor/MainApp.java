package cn.spring.bean.BeanPostProcessor;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.dataModel.BeanDemo;
import cn.spring.TestConstant;

public class MainApp {
   public static void main(String[] args) {
      AbstractApplicationContext context = new ClassPathXmlApplicationContext(TestConstant.beanXml_path);
      BeanDemo obj = (BeanDemo) context.getBean("helloWorld");
      obj.getMessage();
      BeanDemo obj2 = (BeanDemo) context.getBean("helloWorld2");
      obj2.getMessage();
      context.registerShutdownHook();
//      
//      理解：先执行完初始加载，然后再执行bean里面的业务方法。PostProcessor对
//      输出结果：()
//      BeanPostProcessor中：bean:helloWorld执行PostProcessor的BeforeInitialization
//      bean中：执行bean的init
//      BeanPostProcessor中：bean:helloWorld执行PostProcessor的AfterInitialization
//      BeanPostProcessor中：bean:helloWorld2执行PostProcessor的BeforeInitialization
//      bean中：执行bean的init
//      BeanPostProcessor中：bean:helloWorld2执行PostProcessor的AfterInitialization
//      bean中：Your Message : Hello World!- by xml
//      bean中：Your Message : Hello World!- by xml
//      bean中：执行bean的destory
//      bean中：执行bean的destory

   }
}