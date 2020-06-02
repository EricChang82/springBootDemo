package cn;

import java.math.BigDecimal;
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
    /**Purpose:获得执行耗时
     * @author changle
     * Create Time: 2019年10月8日 
     * Version: 1.0
     */
    public static void getTimeEnd(long sTime1) {
        long result = System.currentTimeMillis() - sTime1;
        System.out.println("耗时： " + result);

    }

    /**Purpose:标记开始时间
     * @author changle
     * Create Time: 2019年10月8日 
     * Version: 1.0
     */
    public static long getTimeStart() {
        return System.currentTimeMillis();
    }

    public static boolean isNotNull(String value) {
        boolean isNull = false;
        if (value != null && value.trim().length() > 0) {
            isNull = true;
        }
        return isNull;
    }

    public static boolean isNull(String value) {
        return !isNotNull(value);
    }

    public static int parse2IntValue(String valueString) {
        if (isNull(valueString)) {
            return 0;
        }
        BigDecimal aBigDecimal = new BigDecimal(valueString);
        return aBigDecimal.intValue();
    }

    /**
     *@author changle
     *Create Time: 2019年5月14日 
     *Purpose:
     * @param jobInfo TODO
     */
    public static void exeOneJob(String jobInfo) {
        String startTime = Util.getCurrentTimeStr();
        long tid = Thread.currentThread().getId();
        //        long id = Util.getTimeMillis();
        //        Util.print(Util.getCurrentTimeStr()+"||before"+message+",Id="+id);
        try {
            //            Thread.sleep(10000);
            String endTime = Util.getCurrentTimeStr();
            Util.print("(" + Util.getCurrentTimeStr() + ")" + "," + jobInfo + ",线程ID:" + tid + ",开始时间:" + startTime + ",结束时间:" + endTime);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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
        SimpleDateFormat sfDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
        String ftime = sfDateFormat.format(date);
        return ftime;
    }

    /**
     *@author changle
     *Create Time: 2019年4月19日 
     *Purpose:
     */
    public static void printCurrentTime() {
        System.out.println(Util.getFormatDateTime(new Date()));
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

    public static void printMap2(Map<String, Object> map) {
        System.out.println("遍历map --START");
        Util.print("通过Map.entrySet遍历key和value");
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            Util.print("key= " + entry.getKey() + " and value= " + entry.getValue());
        }
        System.out.println("遍历 map ---END");
    }

    /**
     *@author changle
     *Create Time: 2019年4月22日 
     *Purpose:
     */
    public static long getTimeMillis() {
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
    public static void print(String str) {
        System.out.println(str);
    }
}
