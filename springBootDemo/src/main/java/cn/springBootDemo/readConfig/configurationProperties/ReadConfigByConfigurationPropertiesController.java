package cn.springBootDemo.readConfig.configurationProperties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReadConfigByConfigurationPropertiesController {
    @Autowired
    private ConfigDemo configdemo;
    
    @Autowired  //该注解使用的前提是context中有对象。即类用了@Component注解，或有对应方法用了@Bean
    private ConfigDemo2 configdemo2;
    
    //通过方法读取配置信息.可以实现注入第三方的类中.比如dataSource等
    @Bean
    @ConfigurationProperties("db")
    //该方法放到哪个类下都可以，会自动执行
    public ConfigDemo2 readConfigData() {
       return new ConfigDemo2();
    }
    
    @GetMapping("/readConfig/ConfigurationProperties")
    public String readConfig() {
        String str1= configdemo.toString();
        String str2= configdemo2.toString();
        return str1+"\n"+str2+"dd";

    }
}
