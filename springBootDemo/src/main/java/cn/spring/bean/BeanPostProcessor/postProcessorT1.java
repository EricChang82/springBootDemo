package cn.spring.bean.BeanPostProcessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.Ordered;

import cn.Util;
/**
 * 
 * Project Name:devProject
 * @author changle
 * Purpose:Bean 后置处理器
 * Create Time: 2019年3月4日 
 * Create Specification:
 * Modified Time:
 * Modified by:
 * Modified Specification:
 * Version: 1.0
 */
public class postProcessorT1 implements BeanPostProcessor,Ordered {
    
//    接口中两个方法不能返回null，如果返回null那么在后续初始化方法将报空指针异常或者通过getBean()方法获取不到bena实例对象
//    因为后置处理器从Spring IoC容器中取出bean实例对象没有再次放回IoC容器中
    
   public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
      Util.print("postProcessorT1中：bean:"+beanName+"执行PostProcessor的BeforeInitialization");
      return bean;  
   }
   public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
       Util.print("postProcessorT1中：bean:"+beanName+"执行PostProcessor的AfterInitialization");
      return bean;  
   }
   //该方法返回一整数，默认值为 0，优先级最高，值越大优先级越低
@Override
public int getOrder() {
//    return 2;
    return 1;
}
}