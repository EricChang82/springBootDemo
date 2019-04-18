
package cn.springBootDemo.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import cn.Util;

/**
 * @author changle 
 * Create Time: 2019年4月10日 
 * Purpose:
 */

public class InterceptorDemo extends HandlerInterceptorAdapter{
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        Util.print("拦截器 InterceptorDemo action ");
        return true;
    }
}
