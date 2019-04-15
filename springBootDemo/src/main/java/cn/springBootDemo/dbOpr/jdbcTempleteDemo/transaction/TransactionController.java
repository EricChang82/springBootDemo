package cn.springBootDemo.dbOpr.jdbcTempleteDemo.transaction;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author changle 
 * Create Time: 2019年4月9日 
 * Purpose:
 */
@RestController
public class TransactionController {
    @Autowired //自动根据配置文件中的属性设置 JdbcTemplate
    private JdbcTemplate jdbcTemplateObject;

    /**
     *@author changle
     *Create Time: 2019年4月9日 
     *Purpose:写入数据
     */
    @Transactional
    @GetMapping("/dbOpr/transaction/transactionTest")
    public void insertStudent() throws SQLException {
        String SQL = "insert into Student (name, age) values (?, ?)";
        jdbcTemplateObject.update(SQL, "ABC", 1);
        System.out.println(1 / 0); //抛出异常
        //        System.out.println("111");
        
        String SQ11 = "insert into Student (name, age) values (?, ?)";
        jdbcTemplateObject.update(SQ11, "123", 1);
    }

}
