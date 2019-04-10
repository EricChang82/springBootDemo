package cn.spring.annotations.baseOnJava.importAnnotation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(ConfigA.class)  //注意两个config中不能有同名方法，否则会被覆盖
public class ConfigB {
   @Bean
   public B b() {
       return new B(); 
   }
}

