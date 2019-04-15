package cn.springBootDemo.readConfig.importSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@ImportResource("classpath:cn/springBootDemo/readConfig/importSource/Beans.xml") //导入xml
//@SpringBootApplication
public class ImportSourceMain {

	public static void main(String[] args) {
		SpringApplication.run(ImportSourceMain.class, args);
	}
}
