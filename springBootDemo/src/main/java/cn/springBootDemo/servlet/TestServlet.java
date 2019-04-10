package cn.springBootDemo.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet(urlPatterns="/testServlet")  方式1
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    System.out.println("do get ");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

//	@Bean 该方法不能放到自己的类中
//	public ServletRegistrationBean<TestServlet> regServletBean(){
//	    ServletRegistrationBean<TestServlet> bean = new ServletRegistrationBean<TestServlet>();
//	    bean.setServlet(new TestServlet());
//	    bean.addUrlMappings("/testServlet");
//        return bean;
//	    
//	}
	
	
}
