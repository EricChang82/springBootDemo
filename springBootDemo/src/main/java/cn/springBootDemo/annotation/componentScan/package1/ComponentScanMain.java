package cn.springBootDemo.annotation.componentScan.package1;

import org.springframework.boot.SpringApplication;

//@SpringBootApplication
//@ComponentScan({ "cn.springBootDemo.annotation.componentScan.package1", "cn.springBootDemo.annotation.componentScan.package2" }),若在@SpringBootApplication此处设置需要将路径都包含上
public class ComponentScanMain {
    public static void main(String[] args) {
        SpringApplication.run(ComponentScanMain.class, args);
    }
}
