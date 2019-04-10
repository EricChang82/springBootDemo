package cn.springBootDemo.annotation.componentScan.package2;

import org.springframework.stereotype.Component;

/**
 * @author changle 
 * Create Time: 2019年4月10日 
 * Purpose:
 */
@Component
public class DemoData2 {
    private String value="data2Value12";

    /**
     * @return Returns the value.
     */
    public String getValue() {
        return value;
    }

    /**
     * @param value The value to set.
     */
    public void setValue(String value) {
        this.value = value;
    }
}
