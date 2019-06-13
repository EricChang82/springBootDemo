
package cn.springBootDemo.thymeleaf;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ThymeleafControl {
    @GetMapping("/thymeleafDemo")
    public String thymeleafTest(Model model) {
        model.addAttribute("name", "名称1");
       return "thymeleafDemo"; 
    }
}
