
package cn.springBootDemo.pageRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import cn.dataModel.Greeting;

@Controller
public class PageRequestController {

    @GetMapping("/greeting")
    public String greetingForm(Model model) {
        Greeting newData=  new Greeting();
        newData.setId(111);
        newData.setContent("初始值");
        model.addAttribute("greeting", newData);
        return "/pageRequest/greeting";
    }
 
    @PostMapping("/greeting")
    public String greetingSubmit(@ModelAttribute Greeting greeting) {
        System.out.println(greeting.toString());
        return "/pageRequest/result";
    }
}