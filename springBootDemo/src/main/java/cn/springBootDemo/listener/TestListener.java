package cn.springBootDemo.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener  //方式1 注解加注解即可
public class TestListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("listener:contextInitialized");

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("listener:contextDestroyed");
        
    }

}
