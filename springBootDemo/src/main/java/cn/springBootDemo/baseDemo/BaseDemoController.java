package cn.springBootDemo.baseDemo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BaseDemoController {
    @Value("${project.udf01}")
    private String  msg;
//	@RequestMapping("/hello")
    @GetMapping("/baseDemo")  
	public String hello() {
		return "BaseDemoController "+msg;
	}
}
