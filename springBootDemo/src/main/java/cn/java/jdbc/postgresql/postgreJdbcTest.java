/**
 * devProject
 * @author changle
 * Create Time: 2019年2月20日 
 * Modified Time:
 * Modified by: 
 * Version: 1.0
 */
package cn.java.jdbc.postgresql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Project Name:devProject
 * @author changle
 * Purpose:
 * Create Time: 2019年2月20日 
 * Create Specification:
 * Modified Time:
 * Modified by:
 * Modified Specification:
 * Version: 1.0
 */

public class postgreJdbcTest {
    static String url = "jdbc:postgresql://test8pg.wmsdev.net:5432/wms_v6";
    static String usr = "wms_v6";
    static String psd = "!@#passwd12WSX";

 
    
    public static void main(String[] args) {
        Connection conn = null;
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(url, usr, psd);
            StringBuffer sqlbuff = new StringBuffer();
            sqlbuff.append("select asnNo,asnLineNo,customerId from doc_asn_details where  organizationId='FLUX' and warehouseId='WH_AUTOMATIC' and asnno=? and asnLineNo=?");
            
            PreparedStatement ps = conn.prepareStatement(sqlbuff.toString());  
            ps.setString(1,"000002");
            ps.setString(2,"1");
//            ps.setInt(2,1);
            ResultSet rSet = ps.executeQuery();
            while(rSet.next()){
                System.out.println(rSet.getString(1)+" "+rSet.getString(2));
                System.out.println(rSet.getString(1)+" "+rSet.getInt(2));
            }
            
            rSet.close();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

}
