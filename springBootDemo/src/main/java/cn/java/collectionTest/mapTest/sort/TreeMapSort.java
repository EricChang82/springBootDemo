package cn.java.collectionTest.mapTest.sort;

import java.util.Comparator;
import java.util.TreeMap;

import cn.Util;

public class TreeMapSort {
    public static void main(String[] args) {
        TreeMap<String, Object> map = new TreeMap<String, Object>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                int retValue=Integer.parseInt(o1)-Integer.parseInt(o2);
                System.out.println(retValue);
                return retValue;
            }

        });
        map.put("1", "a");
        map.put("3", "a");
        map.put("4", "a");
//        map.put("5", "a");
//        map.put("2", "a");
        
        Util.printMap2(map);
    }
}
