package cn.springBootDemo.dbOpr.jdbcTempleteDemo.insert;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author changle 
 * Create Time: 2019年4月9日 
 * Purpose:
 */
@RestController
public class InsertController {
    @Autowired
    private JdbcTemplate jdbcTemplateObject;

    /**
     *@author changle
     *Create Time: 2019年4月9日 
     *Purpose:写入数据
     */
    @GetMapping("/dbOpr/jdbcTempleteDemo/insertStudent")
    public void insertStudent() throws SQLException {
        String SQL = "insert into Student (name, age) values (?, ?)";
        jdbcTemplateObject.update(SQL, "a", 1);
    }

}
