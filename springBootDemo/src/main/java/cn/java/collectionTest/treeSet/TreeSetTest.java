package cn.java.collectionTest.treeSet;

import java.util.TreeSet;

import cn.java.Util;

public class TreeSetTest implements Comparable<Object>{
     public static void main(String[] args) {
         TreeSet<Object> treeSet1 = new TreeSet<Object>();
         treeSet1.add("aa");
         treeSet1.add("aa");
         treeSet1.add(new TreeSetTest());
         treeSet1.add(new TreeSetTest());
         
         for (Object obj : treeSet1) {
            Util.print(obj);
        }
         
         
     }

    /* (non-Javadoc)
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    public int compareTo(Object o) {
        Util.print("in compareTo:"+o.toString());
        return 1;
    }
}