package cn.springBootDemo.servlet;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class TestServletCom  {
	@Bean    //方式2
	public ServletRegistrationBean<TestServlet> regServletBean(){
	    ServletRegistrationBean<TestServlet> bean = new ServletRegistrationBean<TestServlet>();
	    bean.setServlet(new TestServlet());
	    bean.addUrlMappings("/testServlet");
        return bean;
	    
	}
	
	
}
