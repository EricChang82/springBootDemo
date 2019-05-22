package cn.java.collectionTest.hashSet;

import java.util.HashSet;

import cn.Util;

public class HashSetTest {
     public static void main(String[] args) {
         HashSet<String> setTmp = new HashSet<String>();
         setTmp.add("aaa");
         setTmp.add("bbb");
         setTmp.add("ccc");
         
         for (String key : setTmp) {
            Util.print(key);
        }
         
         
     }

   
}