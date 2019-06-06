package cn.java.collectionTest.treeMap;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang3.StringUtils;
import org.apache.curator.retry.RetryUntilElapsed;
import org.apache.poi.ss.formula.functions.Finance;

import cn.Util;

/**
 * @author changle 
 * Create Time: 2019年6月5日 
 * Purpose:
 */

public class TreeMapSortTest {
   
    public static void main(String[] args) {
        
        
         TreeMap<String, String> treeMapDatas = new TreeMap<String,  String>(new Comparator<String>() {
             @Override
             public int compare(String o1, String o2) {
                 String[] o1_array=StringUtils.splitByWholeSeparator(o1, "_");
                 String[] o2_array=StringUtils.splitByWholeSeparator(o2, "_");
                 int orderQty=Util.parse2IntValue(o1_array[0]);
                 int orderQty2=Util.parse2IntValue(o2_array[0]);
                 int zoneGrouplist_length_1=Util.parse2IntValue(o1_array[1]);
                 int zoneGrouplist_length_2=Util.parse2IntValue(o2_array[1]);
                 if (orderQty>orderQty2) {
                     return -1;
                  }else if (orderQty<orderQty2) {
                      return 1;
                  }else{
                      if (zoneGrouplist_length_1>zoneGrouplist_length_2) {
                          return 1;
                      }else  if (zoneGrouplist_length_1<zoneGrouplist_length_2) {
                          return -1;
                      }
                  }
                 return 0;
                 }

         });
        
        treeMapDatas.put("5_2_loc","loc1");
        treeMapDatas.put("4_3_loc","loc2");
        treeMapDatas.put("8_1_loc","loc3");
        treeMapDatas.put("8_3_loc","loc3");
        treeMapDatas.put("4_2_loc","locwqwq3");
        treeMapDatas.put("2_1_loc","loc3");
        
//        treeMapDatas.put("1_1_loc",new DataInfo());
//        treeMapDatas.put("2_1_loc",new DataInfo());
//        treeMapDatas.put("1_2_lof",new DataInfo());
//        treeMapDatas.put("1_2_loe",new DataInfo());
//        treeMapDatas.put("2_2_loc",new DataInfo());
//        treeMapDatas.put("1_3_loc",new DataInfo());
//        treeMapDatas.put("12_loc",new DataInfo());
//        treeMapDatas.put("3_loc",new DataInfo());
//        treeMapDatas.put("5_loc",new DataInfo());
//        treeMapDatas.put("61_loc",new DataInfo());
//        treeMapDatas.put("8_loc",new DataInfo());
//        putOnedData(treeMapDatas, 4);
//        putOnedData(treeMapDatas, 1);
//        putOnedData(treeMapDatas, 2);
//        putOnedData(treeMapDatas, 3);

      printMap2(treeMapDatas);
        
    }

    public static void printMap2(Map<String, String> map) {
        System.out.println("遍历map --START");
        Util.print("通过Map.entrySet遍历key和value");
        for (Map.Entry<String, String> entry : map.entrySet()) {
            Util.print("key= " + entry.getKey() + " and value= " +entry.getValue());
        }
        System.out.println("遍历 map ---END");
    }

    /**Purpose:
     * @author changle
     * Create Time: 2019年6月5日 
     * Version: 1.0
     */
    private static void putOnedData(TreeMap<String, DataInfo> treeMapDatas, int orderQty) {
        DataInfo data = new DataInfo();
        data.ID = "orderQty-" + orderQty + "";
        for (int i = 0; i < orderQty; i++) {
            data.orderNoSet.add("O_"+i);
        }
        treeMapDatas.put(data.ID, data);
    }

}
