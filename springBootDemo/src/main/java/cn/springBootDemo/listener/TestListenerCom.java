package cn.springBootDemo.listener;

import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;

//@Component
public class TestListenerCom  {
    
    @Bean
    public ServletListenerRegistrationBean<TestListener> regListener() {
        ServletListenerRegistrationBean<TestListener> bean= new ServletListenerRegistrationBean<TestListener>();
        bean.setListener(new TestListener());
        return bean;
    }

}
