package cn.springBootDemo.annotation.springBootConfiguration;

import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.RestController;

import cn.Util;

@RestController
//@SpringBootApplication
public class AnnotationMain {

    @SuppressWarnings("rawtypes")
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(AnnotationMain.class, args);
        Map map = (Map) context.getBean("createMap"); //注意这里直接获取到这个方法bean
        int age = (int) map.get("age");
        Util.print("age==" + age);
        
        Map map2 = (Map) context.getBean("createMap2"); //注意这里直接获取到这个方法bean
        int age2 = (int) map2.get("age");
        Util.print("age==" + age2);
    }

}