package cn.spring.transaction.codeType;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.Util;

public class MainApp {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring/transaction/codeType/Beans.xml");
        StudentJDBCTemplate studentJDBCTemplate = (StudentJDBCTemplate) context.getBean("studentJDBCTemplate");
        Util.print("------Records creation--------");
        studentJDBCTemplate.create("Zara", 11, 99, 2010);
        studentJDBCTemplate.create("Nuha", 20, 97, 2010);
        studentJDBCTemplate.create("Ayan", 25, 100, 2011);
        Util.print("------Listing all the records--------");
        List<StudentMarks> studentMarks = studentJDBCTemplate.listStudents();
        for (StudentMarks record : studentMarks) {
             Util.print("ID : " + record.getId());
             Util.print(", Name : " + record.getName());
             Util.print(", Marks : " + record.getMarks());
            Util.print(", Year : " + record.getYear());
            Util.print(", Age : " + record.getAge());
        }
    }
}