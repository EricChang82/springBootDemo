/**
 * devProject
 * @author changle
 * Create Time: 2019年3月11日 
 * Modified Time:
 * Modified by: 
 * Version: 1.0
 */
package cn.spring.annotations.baseOnJava.importAnnotation;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Project Name:devProject
 * @author changle
 * Purpose:
 * Create Time: 2019年3月11日 
 * Create Specification:
 * Modified Time:
 * Modified by:
 * Modified Specification:
 * Version: 1.0
 */

public class MainApp {
    
    public static void main(String[] args) {
       ApplicationContext ctx = 
       new AnnotationConfigApplicationContext(ConfigB.class);
       // now both beans A and B will be available...
       A a = ctx.getBean(A.class);
       B b = ctx.getBean(B.class);
       
       System.out.println(a);
       System.out.println(b);
    }
}

