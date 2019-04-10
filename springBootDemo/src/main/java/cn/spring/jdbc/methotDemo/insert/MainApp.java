package cn.spring.jdbc.methotDemo.insert;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.spring.jdbc.methotDemo.StudentJDBCTemplate;

public class MainApp {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring/jdbc/methotDemo/Beans.xml");
        StudentJDBCTemplate studentJDBCTemplate = (StudentJDBCTemplate) context.getBean("studentJDBCTemplate");
        String SQL = "insert into Student (name, age) values (?, ?)";    
        studentJDBCTemplate.jdbcTemplateObject.update(SQL,"name1", 22);
        
    }
}