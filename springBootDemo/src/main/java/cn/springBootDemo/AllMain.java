package cn.springBootDemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

import cn.springBootDemo.readConfig.configurationProperties.ConfigDemo2;

@SpringBootApplication

public class AllMain {

	public static void main(String[] args) {
		SpringApplication.run(AllMain.class, args);
	}
}
