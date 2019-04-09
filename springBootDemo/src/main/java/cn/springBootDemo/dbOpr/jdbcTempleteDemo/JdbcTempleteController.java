package cn.springBootDemo.dbOpr.jdbcTempleteDemo;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author changle 
 * Create Time: 2019年4月9日 
 * Purpose:
 */
@RestController
public class JdbcTempleteController {
    @Autowired //自动根据配置文件中的属性设置 JdbcTemplate
    private JdbcTemplate jdbcTemplateObject;

    @ResponseBody
    @GetMapping("/dbOpr/jdbcTempleteDemo")
    public String printJdbcTemplate() throws SQLException {
        return "" + jdbcTemplateObject;
    }

    /**
     *@author changle
     *Create Time: 2019年4月9日 
     *Purpose:写入数据
     */
    @GetMapping("/dbOpr/insertStudent")
    public void insertStudent() throws SQLException {
        String SQL = "insert into Student (name, age) values (?, ?)";
        jdbcTemplateObject.update(SQL, "a", 1);
    }

    /**
    *@author changle
    *Create Time: 2019年4月9日 
    *Purpose:查询单个数据
    */
    @GetMapping("/dbOpr/getStudent")
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
    @GetMapping("/dbOpr/listStudents")
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
