
package cn.springBootDemo.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import cn.Util;

/**
 * @author changle 
 * Create Time: 2019年4月10日 
 * Purpose:
 */
@WebFilter(urlPatterns="/*")
public class TestFilter implements Filter{

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
       Util.print("doFilter test");
       chain.doFilter(request, response);//放行请求
    }

    @Override
    public void destroy() {
        // TODO Auto-generated method stub
        
    }

}
