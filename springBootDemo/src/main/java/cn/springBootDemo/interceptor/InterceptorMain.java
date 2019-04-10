package cn.springBootDemo.interceptor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

//@SpringBootApplication
public class InterceptorMain extends WebMvcConfigurerAdapter {

    public static void main(String[] args) {
        SpringApplication.run(InterceptorMain.class, args);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(myInterceptor()).addPathPatterns("/*");
    }

    /**
    *@author changle
    *Create Time: 2019年4月10日 
    *Purpose:
    */
    @Bean
    public InterceptorDemo myInterceptor() {
        return new InterceptorDemo();

    }
}
