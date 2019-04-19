package cn;

import java.text.SimpleDateFormat;
import java.util.Date;

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

}
