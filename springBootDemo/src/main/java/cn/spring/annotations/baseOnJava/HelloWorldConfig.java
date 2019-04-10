package cn.spring.annotations.baseOnJava;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import cn.spring.annotations.JSR25.HelloWorld;  配置的类需要和调用地方的保持一致

@Configuration
public class HelloWorldConfig {
   @Bean 
   public HelloWorld helloWorld(){
      return new HelloWorld();
   }
}