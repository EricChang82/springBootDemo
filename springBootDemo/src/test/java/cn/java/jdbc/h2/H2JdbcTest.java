
package cn.java.jdbc.h2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

import org.junit.Test;

import cn.Util;

public class H2JdbcTest {

    static String calssName = "org.h2.Driver";
//    static String url = "jdbc:h2:mem:test2";
    static String url = "jdbc:h2:~/test3";
    static String usr = "sa";
    static String psd = "";

    /**
     * Purpose:循环创建测试语句  --测试与map比较排序逻辑速度
     * @author changle
     * Create Time: 2020年3月25日 
     * @throws Exception
     * Version: 1.0
     */
    @Test
    public void test000() {
        Connection connection = null;
        Statement stnt =null;
        try {
            Class.forName(calssName);
            connection = DriverManager.getConnection(url, usr, psd);
//            String crteatTalbeSql = " CREATE TABLE H2TEST2(ID INT PRIMARY KEY, ZONEGROUPLIST VARCHAR(255), ORDERCOUNT INT)";
            StringBuffer crteatTalbeSqlBuffer = new StringBuffer();
            crteatTalbeSqlBuffer.append("CREATE TABLE H2TEST2(");
            crteatTalbeSqlBuffer.append("id INT PRIMARY KEY,");
            crteatTalbeSqlBuffer.append("zonegrouplist VARCHAR(255),");
            crteatTalbeSqlBuffer.append("activeFlag VARCHAR(255),");
            crteatTalbeSqlBuffer.append("locationId VARCHAR(255),");
            crteatTalbeSqlBuffer.append("putToLocation VARCHAR(255),");
            crteatTalbeSqlBuffer.append("pickToLocation VARCHAR(255),");
            crteatTalbeSqlBuffer.append("pickingMode VARCHAR(255),");
            crteatTalbeSqlBuffer.append("replenishmentMode VARCHAR(255),");
            crteatTalbeSqlBuffer.append("currentVersion INT,");
            crteatTalbeSqlBuffer.append("eaCount INT,");
            crteatTalbeSqlBuffer.append("orderCount INT,");
            crteatTalbeSqlBuffer.append("length  DECIMAL(18,8) NULL,");
            crteatTalbeSqlBuffer.append("width  DECIMAL(18,8) NULL,");
            crteatTalbeSqlBuffer.append("height  DECIMAL(18,8) NULL,");
            crteatTalbeSqlBuffer.append("mix_flag  CHAR(1) DEFAULT 'N' NOT NULL");
            crteatTalbeSqlBuffer.append(")");
             stnt = connection.createStatement();
            stnt.execute(crteatTalbeSqlBuffer.toString());
            
            //#1S-创建索引-S
            String indexSql ="CREATE INDEX orderCountIndex ON H2TEST2(orderCount) ";
            stnt = connection.createStatement();
            stnt.executeUpdate(indexSql);
            //#2E-创建索引-E 
            
            //#1S-构造map、H2 数据-S
            //        zoneGroupList   OrderCount
            Random rd = new Random();
            TreeMap<String, ZoneGroupListCacheData> candidateDataMapZoneGroupForKey = new TreeMap<String, ZoneGroupListCacheData>();//按库位组优化时，需要对该map 排序
           int testDatasNum=50000;
            for (int i = 0; i < testDatasNum; i++) {
                ZoneGroupListCacheData data = new ZoneGroupListCacheData();
                data.setId(i+"");
                data.setZoneGroupList("number:" + i);
                data.setActiveFlag("Y");
                data.setLocationId("LOC"+i);
                data.setPutToLocation("PUTTLOC"+i);
                data.setPickToLocation("PICTOLOC"+i);
                data.setPickingMode("PM"+i);
                data.setReplenishmentMode("RPM"+i);
                data.setEaCount(rd.nextInt());
                data.setOrderCount(rd.nextInt(100000));
                data.setLength(100);
                data.setWidth(200);
                data.setHeight(300);
                data.setMix_flag("Y");
                data.setCurrentVersion(i*100);
                candidateDataMapZoneGroupForKey.put(data.getZoneGroupList(), data);
                //                insert into H2TEST2 (id,zoneGroupList ,orderCount ) values(1,'dfd',22)
//                String insertSql = "insert into H2TEST2 (id,zoneGroupList ,orderCount) values(" + i + ",'" + data.getZoneGroupList() + "'," + data.getOrderCount() + ")";
                StringBuffer insertSqlBuffer = new StringBuffer();
                insertSqlBuffer.append("insert into H2TEST2 (");
                insertSqlBuffer.append("id ,");
                insertSqlBuffer.append("zonegrouplist ,");
                insertSqlBuffer.append("activeFlag,");
                insertSqlBuffer.append("locationId,");
                insertSqlBuffer.append("putToLocation,");
                insertSqlBuffer.append("pickToLocation,");
                insertSqlBuffer.append("pickingMode,");
                insertSqlBuffer.append("replenishmentMode,");
                insertSqlBuffer.append("eaCount ,");
                insertSqlBuffer.append("orderCount,");
                insertSqlBuffer.append("length ,");
                insertSqlBuffer.append("width ,");
                insertSqlBuffer.append("height,");
                insertSqlBuffer.append("mix_flag,");
                insertSqlBuffer.append("currentVersion");
                insertSqlBuffer.append(")values(");
                insertSqlBuffer.append(data.getId()).append(",");
                insertSqlBuffer.append("\'").append(data.getZoneGroupList()).append("\',");
                insertSqlBuffer.append("\'").append(data.getActiveFlag()).append("\',");
                insertSqlBuffer.append("\'").append(data.getLocationId()).append("\',");
                insertSqlBuffer.append("\'").append(data.getPutToLocation()).append("\',");
                insertSqlBuffer.append("\'").append(data.getPickToLocation()).append("\',");
                insertSqlBuffer.append("\'").append(data.getPickingMode()).append("\',");
                insertSqlBuffer.append("\'").append(data.getReplenishmentMode()).append("\',");
                insertSqlBuffer.append(data.getEaCount()).append(",");
                insertSqlBuffer.append(data.getOrderCount()).append(",");
                insertSqlBuffer.append(data.getLength()).append(",");
                insertSqlBuffer.append(data.getWidth()).append(",");
                insertSqlBuffer.append(data.getHeight()).append(",");
                insertSqlBuffer.append("\'").append(data.getMix_flag()).append("\',");
                insertSqlBuffer.append(data.getCurrentVersion());
                insertSqlBuffer.append(")");
                stnt = connection.createStatement();
                stnt.execute(insertSqlBuffer.toString());
            }
            //#2E-构造map、H2 数据-E 
            
            
            //#1S-逻辑测试-S
            testIn(connection, candidateDataMapZoneGroupForKey);// 测试In
//            testOrderBy(connection, candidateDataMapZoneGroupForKey);// 测试排序
//            testCompare(connection, candidateDataMapZoneGroupForKey);// 测试大于筛选
            
            //#2E-逻辑测试-E 
            
            
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            try {
                connection.close();
                stnt.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
         
        }
    }
    /**Purpose:测试In
     * @author changle
     * Create Time: 2020年3月26日 
     * @param connection
     * @param candidateDataMapZoneGroupForKey
     * @return
     * @throws SQLException
     * Version: 1.0
     */
    private void testIn(Connection connection, TreeMap<String, ZoneGroupListCacheData> candidateDataMapZoneGroupForKey) throws SQLException {
        Statement stnt;
        ResultSet rs =null;
        String whereIn="'number:2','number:5','number:3353','number:22674','number:12778'";
        //输出结果
        System.out.println("===========map 方式排序开始===============");
        long startTime = Util.getTimeStart();
        int resultCount=0;
        int limitNum=10000;
        Iterator<String> iter = candidateDataMapZoneGroupForKey.keySet().iterator();
        while (iter.hasNext()) {
            String key = (String) iter.next();
            ZoneGroupListCacheData data = candidateDataMapZoneGroupForKey.get(key);
            if (whereIn.contains("'"+data.getZoneGroupList()+"'")) {
                resultCount++;
            }
        }
        System.out.println("whereIn 的数据共有"+resultCount);
        Util.getTimeEnd(startTime);

        System.out.println("===========H2 方式排序开始===============");
        resultCount=0;
         startTime = Util.getTimeStart();
        String querySql = "select zoneGroupList,orderCount,EaCount from H2TEST2 where zoneGroupList in("+whereIn+")  ";
        
        stnt = connection.createStatement();
         rs = stnt.executeQuery(querySql);
        while (rs.next()) {
            resultCount++;
            //                System.out.println(" H2 处理： OrderCount:" + rs.getInt(2) + "  ," + rs.getString(1));
        }
        System.out.println("whereIn 的数据共有"+resultCount);
        Util.getTimeEnd(startTime);
    }
    /**Purpose:测试大于
     * @author changle
     * Create Time: 2020年3月26日 
     * @param connection
     * @param candidateDataMapZoneGroupForKey
     * @return
     * @throws SQLException
     * Version: 1.0
     */
    private void testCompare(Connection connection, TreeMap<String, ZoneGroupListCacheData> candidateDataMapZoneGroupForKey) throws SQLException {
        Statement stnt;
        ResultSet rs =null;
        //输出结果
        System.out.println("===========map 方式排序开始===============");
        long startTime = Util.getTimeStart();
        int resultCount=0;
        int limitNum=100;
        Iterator<String> iter = candidateDataMapZoneGroupForKey.keySet().iterator();
        while (iter.hasNext()) {
            String key = (String) iter.next();
            ZoneGroupListCacheData data = candidateDataMapZoneGroupForKey.get(key);
            if (data.getEaCount()>limitNum) {
                resultCount++;
            }
        }
        System.out.println("EaCount>100的共有"+resultCount);
        Util.getTimeEnd(startTime);

        System.out.println("===========H2 方式排序开始===============");
        resultCount=0;
         startTime = Util.getTimeStart();
        String querySql = "select zoneGroupList,orderCount,EaCount from H2TEST2 where EaCount>100  ";
        
        stnt = connection.createStatement();
         rs = stnt.executeQuery(querySql);
        while (rs.next()) {
            resultCount++;
            //                System.out.println(" H2 处理： OrderCount:" + rs.getInt(2) + "  ," + rs.getString(1));
        }
        System.out.println("EaCount>100的共有"+resultCount);
        Util.getTimeEnd(startTime);
    }
   
    /**Purpose:测试排序
     * @author changle
     * Create Time: 2020年3月26日 
     * @param connection
     * @param candidateDataMapZoneGroupForKey
     * @return
     * @throws SQLException
     * Version: 1.0
     */
    private void testOrderBy(Connection connection, TreeMap<String, ZoneGroupListCacheData> candidateDataMapZoneGroupForKey) throws SQLException {
        Statement stnt;
        ResultSet rs =null;
        //输出结果
        System.out.println("===========map 方式排序开始===============");
        long startTime = Util.getTimeStart();
        List<Map.Entry<String, ZoneGroupListCacheData>> list_zoneGroup = AlgorithmUtil.sortZoneGroupListMap(candidateDataMapZoneGroupForKey);
        for (int i = 0; i < list_zoneGroup.size(); i++) {
            String zoneGroupList = list_zoneGroup.get(i).getKey();
            ZoneGroupListCacheData data = candidateDataMapZoneGroupForKey.get(zoneGroupList);
//                System.out.println(" map 处理： OrderCount:" + data.getOrderCount() + "  ," + data.getZoneGroupList());
        }
        Util.getTimeEnd(startTime);

        System.out.println("===========H2 方式排序开始===============");
         startTime = Util.getTimeStart();
        String querySql = "select zoneGroupList,orderCount from H2TEST2 order by orderCount asc ";
        
        stnt = connection.createStatement();
         rs = stnt.executeQuery(querySql);
        while(rs.next()){
//                System.out.println(" H2 处理： OrderCount:" + rs.getInt(2) + "  ," + rs.getString(1));
        }
        Util.getTimeEnd(startTime);
    }
   
}
