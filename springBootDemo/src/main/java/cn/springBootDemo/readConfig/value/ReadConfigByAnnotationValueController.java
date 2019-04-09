package cn.springBootDemo.readConfig.value;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * Project Name:springBootDemo
 * @author changle
 * Purpose:通过注解读取配置文件
 * Create Time: 2019年4月8日 
 * Create Specification:
 * Modified Time:
 * Modified by:
 * Modified Specification:
 * Version: 1.0
 */
@RestController
public class ReadConfigByAnnotationValueController {
    @Value("${project.udf01}")
    private String msg;
    @Value("${project.udf02}")
    private String msg2;

    @GetMapping("/readConfig/value")
    public String value() {
        return "AnnotationController: " + msg + "||" + msg2;
    }
}
