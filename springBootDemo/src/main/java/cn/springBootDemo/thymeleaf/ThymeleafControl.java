
package cn.springBootDemo.thymeleaf;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ThymeleafControl {
    @GetMapping("/thymeleafDemo")
    public String thymeleafTest(Model model) {
        model.addAttribute("name", "view_htmlTest");
        List<String> list=new ArrayList<String>();
        list.add("1s");
        list.add("2s");
        list.add("3s");
        model.addAttribute("list",list);

        
        
        
       return "thymeleafDemo"; 
    }
}
