/**
 * springBootDemo
 * @author changle
 * Create Time: 2019年4月8日 
 * Modified Time:
 * Modified by: 
 * Version: 1.0
 */
package cn.springBootDemo.readConfig.configurationProperties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Project Name:springBootDemo
 * @author changle
 * Purpose:
 * Create Time: 2019年4月8日 
 * Create Specification:
 * Modified Time:
 * Modified by:
 * Modified Specification:
 * Version: 1.0
 */

@Setter@Getter
@ToString  //通过lombok运行时自动生成方法
public class ConfigDemo2 {
    
    private String userName;
    private String passWord;
}
