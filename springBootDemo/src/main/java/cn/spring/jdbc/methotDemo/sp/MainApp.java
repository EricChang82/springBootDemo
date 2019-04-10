package cn.spring.jdbc.methotDemo.sp;

import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import cn.spring.jdbc.methotDemo.StudentJDBCTemplate;

public class MainApp {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring/jdbc/methotDemo/Beans.xml");
        StudentJDBCTemplate studentJDBCTemplate = (StudentJDBCTemplate) context.getBean("studentJDBCTemplate");
        //#1S-执行-S
        studentJDBCTemplate.jdbcCall.setProcedureName("getRecord");
        SqlParameterSource in = new MapSqlParameterSource().addValue("in_id2", 1);
        Map<String, Object> out = studentJDBCTemplate.jdbcCall.execute(in);
        System.out.println("out_name:"+(String) out.get("out_name"));
        System.out.println("out_age:"+(Integer) out.get("out_age"));

        //#2E-执行-E 
    }
}