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

    
    @GetMapping("/view_htmlTest")
    public String  htmlTest() {
      //访问templates下的html，也需要在pom 中加上thymeleaf的依赖
       return "htmlTest";  //或返回：   return "htmlTest.html";  
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
