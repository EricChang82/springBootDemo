package cn;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.quartz.JobDataMap;

/**
 * @author changle 
 * Create Time: 2019年4月18日 
 * Purpose:
 */

public class Util {
    
    public static void print(Object message) {
        System.out.println(message);
    }
    /**
     *@author changle
     *Create Time: 2019年4月19日 
     *Purpose:
     */
    public static void printDateTime(Date date) {
        String ftime = getFormatDateTime(date);
        Util.print(ftime);
    }
    /**
     *@author changle
     *Create Time: 2019年4月19日 
     *Purpose:
     */
    public static String getFormatDateTime(Date date) {
        SimpleDateFormat sfDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String ftime =sfDateFormat.format(date);
        return ftime;
    }
    /**
     *@author changle
     *Create Time: 2019年4月19日 
     *Purpose:
     */
    public static void printCurrentTime() {
         Util.getFormatDateTime(new Date());
    }
    
    public static String getCurrentTimeStr() {
        return getFormatDateTime(new Date());
    }
    /**
     *@author changle
     *Create Time: 2019年4月22日 
     *Purpose:一般来讲使用entrySet的方式进行遍历是效率最高的，因为hashMap内部的存储结构就是基于Entry的数组，在用这种方式进行遍历时，只需要遍历一次即可。而使用其他方式的时间复杂度可以会提高，例如：keySet方式，每次都需要通过key值去计算对应的hash,然后再通过hash获取对应的结果值，因此效率较低。
     */
    public static void printMap(JobDataMap paraMap) {
        System.out.println("遍历map --START");
        for (Map.Entry<String, Object> entry : paraMap.entrySet()) {
            System.out.println(entry.getKey() + "=" + entry.getValue());
        }
        System.out.println("遍历 map ---END");
    }
    
    /**
     *@author changle
     *Create Time: 2019年4月22日 
     *Purpose:
     */
    public static  long getTimeMillis() {
        return System.currentTimeMillis();
    }
    /**
     *@author changle
     *Create Time: 2019年4月22日 
     *Purpose:
     */
    public static void printTimeMillis() {
       Util.print(System.currentTimeMillis());

    }
}
