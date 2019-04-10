package cn.springBootDemo.annotation.componentScan.package1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.springBootDemo.annotation.componentScan.package2.DemoData2;

@RestController
@ComponentScan({"cn.springBootDemo.annotation.componentScan.package2" })//也可以在此处指定扫描下package2
public class ComponentScanController {
    @Autowired
    private DemoData1 data1;
    @Autowired
    private DemoData2 data2;

    @GetMapping("/annotation/componentScan")
    public String componentScan() {

        //        return data1.getValue();
        return data1.getValue() + data2.getValue();
    }
}
