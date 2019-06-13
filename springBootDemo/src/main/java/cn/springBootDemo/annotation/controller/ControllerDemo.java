package cn.springBootDemo.annotation.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author changle 
 * Create Time: 2019年5月10日 
 * Purpose:
 */
@Controller
public class ControllerDemo {

    
    @GetMapping("/htmlTest")
    public String  htmlTest() {
       return "htmlTest";
    }
    @GetMapping("/ftlTest")
    public String  ftlTest() {
        return "ftlTest";
    }
    @GetMapping("/ftlTestPara")
    public String  ftlTestPara(Map<String, Object> paraMap) {
        paraMap.put("name", "张三");
        return "ftlTestPara";
    }
}
