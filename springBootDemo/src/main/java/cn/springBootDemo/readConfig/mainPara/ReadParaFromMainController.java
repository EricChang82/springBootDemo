package cn.springBootDemo.readConfig.mainPara;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * 
 * @author changle 
 * Create Time: 2019年4月9日 
 * Purpose:通过main arg读取传递的参数（运行时需要在run的配置中，设置参数）
 */
@RestController
public class ReadParaFromMainController {
    @Autowired
    private ApplicationArguments applicationarguments;  //封装了main中的arg参数
    
    @GetMapping("/readConfig/mainPara")
    public String  readConfig() {
//        Util.print(applicationarguments.getNonOptionArgs());
        return applicationarguments.getNonOptionArgs().toString();
        
    }
}
