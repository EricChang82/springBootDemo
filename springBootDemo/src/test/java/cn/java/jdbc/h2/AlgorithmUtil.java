
package cn.java.jdbc.h2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

/**
 * Project Name:wmsv5-a31OubBizSrv
 * @author changle
 * Purpose:程序算法类
 * Create Time: 2017年3月21日 
 * Create Specification:
 * Modified Time:
 * Modified by:
 * Modified Specification:
 * Version: 1.0
 */

public class AlgorithmUtil {
    public static List<Map.Entry<String, ZoneGroupListCacheData>> sortZoneGroupListMap(TreeMap<String, ZoneGroupListCacheData> map) {
        List<Map.Entry<String, ZoneGroupListCacheData>> list = new ArrayList<Map.Entry<String, ZoneGroupListCacheData>>(map.entrySet());
        // 通过比较器来实现排序
        Collections.sort(list, new Comparator<Map.Entry<String, ZoneGroupListCacheData>>() {
            // 排序
            public int compare(Entry<String, ZoneGroupListCacheData> o1, Entry<String, ZoneGroupListCacheData> o2) {
                ZoneGroupListCacheData data1 = o1.getValue();
                ZoneGroupListCacheData data2 = o2.getValue();
                if (data1.getOrderCount() > data2.getOrderCount()) {
                    return -1;
                } else if (data1.getOrderCount() < data2.getOrderCount()) {
                    return 1;
                } else {
                    if (o1.getKey().length() > o2.getKey().length()) {
                        return 1;
                    } else if (o1.getKey().length() < o2.getKey().length()) {
                        return -1;
                    }
                }
                return 0;
            }
        });

        return list;
    }
}
