package cn.springBootDemo.readConfig.importSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.dataModel.BeanDemo;

@RestController
public class ImportSourceController {
    @Autowired  
    private BeanDemo beanDemo;  //通过@ImportResource 导入的xml配置进行获取
    
    @GetMapping("/importSourceTest")  
	public String importSourceTest() {
		return beanDemo.getMessage();
	}
}
