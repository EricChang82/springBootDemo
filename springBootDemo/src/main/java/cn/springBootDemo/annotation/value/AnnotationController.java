package cn.springBootDemo.annotation.value;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AnnotationController {
    @Value("${project.udf01}")
    private String msg;
    @Value("${project.udf02}")
    private String msg2;

    @GetMapping("/annotation")
    public String hello() {
        return "AnnotationController: " + msg + "||" + msg2;
    }
}
