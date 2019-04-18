package cn.spring.jdbc.methotDemo.update;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.Util;
import cn.spring.jdbc.methotDemo.Student;
import cn.spring.jdbc.methotDemo.StudentJDBCTemplate;

public class MainApp {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring/jdbc/baseDemo/Beans.xml");
        StudentJDBCTemplate studentJDBCTemplate = (StudentJDBCTemplate) context.getBean("studentJDBCTemplate");
        
//        studentJDBCTemplate.create("Zara", 11);
     
//        //更新
        studentJDBCTemplate.update(2, 20);
        Student student = studentJDBCTemplate.getStudent(2);
        System.out.print("ID : " + student.getId());
        System.out.print(", Name : " + student.getName());
        Util.print(", Age : " + student.getAge());
    }
}