package cn.springBootDemo.baseSimple;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

//	@RequestMapping("/hello")
    @GetMapping("/")  
	public String hello() {
		return "helloWorld";
	}
}
