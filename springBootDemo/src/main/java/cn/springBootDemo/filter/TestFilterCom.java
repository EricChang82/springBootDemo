
package cn.springBootDemo.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author changle 
 * Create Time: 2019年4月10日 
 * Purpose:
 */
@Component
public class TestFilterCom  {
    @Bean
    private FilterRegistrationBean<TestFilter> regBean() {
        FilterRegistrationBean<TestFilter> bean = new FilterRegistrationBean<TestFilter>();
        bean.setFilter(new TestFilter());
        bean.addUrlPatterns("/*");
        return bean;
    }
}
