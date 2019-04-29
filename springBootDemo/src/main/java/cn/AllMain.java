package cn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

import cn.springBootDemo.servlet.TestServlet;

@SpringBootApplication
@ServletComponentScan   //用到servlet时需要增加该注解
public class AllMain {
    @Bean
    public ServletRegistrationBean<TestServlet> regServletBean(){
        ServletRegistrationBean<TestServlet> bean = new ServletRegistrationBean<TestServlet>();
        bean.setServlet(new TestServlet());
        bean.addUrlMappings("/testServlet");
        return bean;
        
    }
	public static void main(String[] args) {
		SpringApplication.run(AllMain.class, args);
	}
}
