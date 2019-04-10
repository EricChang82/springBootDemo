package cn.java.collectionTest.treeSet;

import java.util.TreeSet;

public class TreeSetTest implements Comparable<Object>{
     public static void main(String[] args) {
         TreeSet<Object> treeSet1 = new TreeSet<>();
         treeSet1.add("aa");
         treeSet1.add("aa");
         treeSet1.add(new TreeSetTest());
         treeSet1.add(new TreeSetTest());
         
         for (Object obj : treeSet1) {
            System.out.println(obj);
        }
         
         
     }

    /* (non-Javadoc)
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    @Override
    public int compareTo(Object o) {
        System.out.println("in compareTo:"+o.toString());
        return 1;
    }
}