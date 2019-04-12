package cn.springBootDemo.dbOpr.getDataSource;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author changle 
 * Create Time: 2019年4月9日 
 * Purpose:
 */
@Controller
public class DruidDataSourceController {
    @Autowired    //自动根据配置文件中的属性设置 dataSource
    private DataSource dataSource;
    @ResponseBody
    @GetMapping("/dataSource/printDataSource")
    public String printDataSource() throws SQLException {
        return dataSource.getConnection()+"<br>"+dataSource;
    }
    
}
