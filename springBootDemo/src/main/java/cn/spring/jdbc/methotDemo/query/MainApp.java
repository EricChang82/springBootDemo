package cn.spring.jdbc.methotDemo.query;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.Util;
import cn.spring.jdbc.methotDemo.Student;
import cn.spring.jdbc.methotDemo.StudentJDBCTemplate;
import cn.spring.jdbc.methotDemo.StudentMapper;

public class MainApp {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring/jdbc/methotDemo/Beans.xml");
        StudentJDBCTemplate studentJDBCTemplate = (StudentJDBCTemplate) context.getBean("studentJDBCTemplate");
        //查询
        String sql = "select * from Student";
        List<Student> students = studentJDBCTemplate.jdbcTemplateObject.query(sql, new StudentMapper());
        for (Student record : students) {
            Util.print("ID : " + record.getId());
            Util.print(", Name : " + record.getName());
            Util.print(", Age : " + record.getAge());
        }
    }
}