package cn.springBootDemo.dbOpr.jdbcTempleteDemo.delete;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author changle 
 * Create Time: 2019年4月9日 
 * Purpose:多种delete demo
 */
@RestController
public class DeleteController {
    @Autowired //自动根据配置文件中的属性设置 JdbcTemplate
    private JdbcTemplate jdbcTemplateObject;

    /**
     *@author changle
     *Create Time: 2019年4月9日 
     *Purpose:写入数据
     */
    @GetMapping("/dbOpr/dbcTempleteDemo//insertStudent")
    public void insertStudent() throws SQLException {
        String SQL = "insert into Student (name, age) values (?, ?)";
        jdbcTemplateObject.update(SQL, "a", 1);
    }

}
