package cn.springBootDemo.baseDemo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BaseDemoController {
    @Value("${project.udf01}")
    private String  msg;
//	@RequestMapping("/hello")
    @GetMapping("/")  
	public String hello() {
		return "启动正常===>BaseDemoController "+msg;
	}
    @GetMapping("/getPara/{para}")
    public String getPara(@PathVariable("para") String para) {
        return "获得传参："+para;
    }
    @GetMapping("/getPara/{para}/{para2}")
    public String get2Para(@PathVariable("para") String para,@PathVariable("para2") String para2) {
        return "获得传参："+para+"||"+para2;
    }
}
