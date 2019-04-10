package cn.springBootDemo.interceptor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InterceptorController {
    @GetMapping("/interceptorTest")  
	public String interceptorTest() {
		return "拦截器测试";
	}
}
