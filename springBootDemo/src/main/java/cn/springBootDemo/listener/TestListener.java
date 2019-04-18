package cn.springBootDemo.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import cn.Util;

@WebListener  //方式1 注解加注解即可
public class TestListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        Util.print("listener:contextInitialized");

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        Util.print("listener:contextDestroyed");
        
    }

}
