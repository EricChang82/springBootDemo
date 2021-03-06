package cn.springBootDemo.annotation.exceptionProcess;

import java.util.HashMap;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cn.Util;
@RestController
@ControllerAdvice
public class MyControllerAdvice {
    
    @ResponseBody
    @ExceptionHandler(value=Exception.class)
    public HashMap<String, Object> errorHandle(Exception exception) {
        HashMap<String, Object> exceptionMap = new HashMap<String, Object>();
        exceptionMap.put("exeMsg","捕获到的异常:"+ exception.getMessage());
        return exceptionMap;
    }
    @ResponseBody
    @ExceptionHandler(value=UdfException.class)
    public HashMap<String, Object> errorHandleForUDF(UdfException exception) {
        HashMap<String, Object> exceptionMap = new HashMap<String, Object>();
        exceptionMap.put("exeMsg","捕获自定义的异常:"+ exception.getExeMessage());
        return exceptionMap;
    }
    @GetMapping("/annotation/test") 
    public void test() throws Exception {
        Util.print(1/0);
    }
    
    @GetMapping("/annotation/testUdf")
    public void testUdf() throws UdfException {
        throw new UdfException("自定义异常");
//        Util.print(1/0);
    }
    
}