package cn.java.classProp;

import java.util.ArrayList;

import org.apache.yetus.audience.InterfaceAudience.Public;

/**
 * @author changle 
 * Create Time: 2019年5月8日 
 * Purpose:
 */

public class PrintClassNature {
    
     public static void main(String[] args) {
//         https://www.cnblogs.com/langtianya/p/5442134.html
         //获得类的简洁名称
        System.out.println(PrintClassNature.class.getCanonicalName());
        System.out.println(PrintClassNature.class.getName());
        
    }

}
