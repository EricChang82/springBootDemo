package cn.spring.transaction.xmlType.demo1;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.Util;

public class MainApp {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring/transaction/xmlType/demo1/Beans.xml");
        StudentDAO studentJDBCTemplate = (StudentDAO) context.getBean("studentJDBCTemplate");
        Util.print("------Records creation--------");
        studentJDBCTemplate.create("Zara", 11, 99, 2010);
        studentJDBCTemplate.create("Nuha", 20, 97, 2010);
        studentJDBCTemplate.create("Ayan", 25, 100, 2011);
        Util.print("------Listing all the records--------");
        List<StudentMarks> studentMarks = studentJDBCTemplate.listStudents();
        for (StudentMarks record : studentMarks) {
            System.out.print("ID : " + record.getId());
            System.out.print(", Name : " + record.getName());
            System.out.print(", Marks : " + record.getMarks());
            System.out.print(", Year : " + record.getYear());
            Util.print(", Age : " + record.getAge());
        }
    }
}