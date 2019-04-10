package cn.spring.jdbc.methotDemo.query;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
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
            System.out.print("ID : " + record.getId());
            System.out.print(", Name : " + record.getName());
            System.out.println(", Age : " + record.getAge());
        }
    }
}