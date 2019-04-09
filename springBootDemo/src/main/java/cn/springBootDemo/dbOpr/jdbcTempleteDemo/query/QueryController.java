package cn.springBootDemo.dbOpr.jdbcTempleteDemo.query;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.springBootDemo.dbOpr.jdbcTempleteDemo.Student;
import cn.springBootDemo.dbOpr.jdbcTempleteDemo.StudentMapper;

/**
 * @author changle 
 * Create Time: 2019年4月9日 
 * Purpose:
 */
@RestController
public class QueryController {
    @Autowired 
    private JdbcTemplate jdbcTemplateObject;
    /**
    *@author changle
    *Create Time: 2019年4月9日 
    *Purpose:查询单个数据
    */
    @GetMapping("/dbOpr/jdbcTempleteDemo/getStudent")
    public String getStudent(String name) {
        if (name==null ) {
            name="a";
        }
        String SQL = "select * from Student where name = ?";
        Student student = jdbcTemplateObject.queryForObject(SQL, new Object[] { name }, new StudentMapper());
        return student.toString();
    }

    /**
     *@author changle
     *Create Time: 2019年4月9日 
     *Purpose:查询多条数据
     */
    @GetMapping("/dbOpr/dbcTempleteDemo/listStudents")
    public String listStudents() {
        StringBuffer retBuff = new StringBuffer();
        String SQL = "select * from Student";
        List<Student> students = jdbcTemplateObject.query(SQL, new StudentMapper());
        for (Student record : students) {
            retBuff.append("ID : " + record.getId());
            retBuff.append(", Name : " + record.getName());
            retBuff.append(", Age : " + record.getAge());
            retBuff.append("<br>");
        }
        return retBuff.toString();
    }

}
