package cn.springBootDemo.readConfig.propertysource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PropertysourceController {
    @Autowired
    private Person  person;

    @GetMapping("/person")
    public String person() {
        return person.toString();
    }
}
