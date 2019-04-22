package cn.java.dateTime;

import org.quartz.DateBuilder;

/**
 * @author changle 
 * Create Time: 2019年4月22日 
 * Purpose:
 */

public class DateTimeDemo {
    public static void main(String[] args) {
        System.out.println(DateBuilder.newDate().toString()); //DateBuilder类包含很多方法，可以很方便地构造表示不同时间点的java.util.Date实例（如定义下一个小时为偶数的时间点，如果当前时间为9:43:27，则定义的时间为10:00:00
    }
}
