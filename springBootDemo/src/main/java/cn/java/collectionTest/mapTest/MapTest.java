package cn.java.collectionTest.mapTest;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import cn.java.Util;

public class MapTest{
     public static void main(String[] args) {
      Map<String, String> map = new HashMap<String, String>();
      map.put("1", "value1");
      map.put("2", "value2");
      map.put("3", "value3");
      
      //第一种：普遍使用，二次取值
      Util.print("通过Map.keySet遍历key和value：");
      for (String key : map.keySet()) {
       Util.print("key= "+ key + " and value= " + map.get(key));
      }
//      SortedSet 
//      HashSet
      //第二种
      Util.print("通过Map.entrySet使用iterator遍历key和value：");
      Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
      while (it.hasNext()) {
       Map.Entry<String, String> entry = it.next();
       Util.print("key= " + entry.getKey() + " and value= " + entry.getValue());
      }
      
      //第三种：推荐，尤其是容量大时
      Util.print("通过Map.entrySet遍历key和value");
      for (Map.Entry<String, String> entry : map.entrySet()) {
       Util.print("key= " + entry.getKey() + " and value= " + entry.getValue());
      }
    
      //第四种
      Util.print("通过Map.values()遍历所有的value，但不能遍历key");
      for (String v : map.values()) {
       Util.print("value= " + v);
      }
     }
}