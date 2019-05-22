package com.alibaba.easyexcel.test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.alibaba.excel.support.ExcelTypeEnum;

import cn.Util;

public class WriteTestUDF {

    @Test
    public void writeV2007() {
        try {
            Util.printCurrentTime();
            String sheetName = "第一个sheet";
            OutputStream fileOutputStream = new FileOutputStream("E:\\1工作区\\resources\\W"+Math.random()+".xls");
            ArrayList<String> headerList = getSheetColumnName();
            List<List<Object>> sheetDatas = getSheetDatas();
            ExcelTypeEnum excelType = ExcelTypeEnum.XLS;
            EasyExcelHelper.expXLSX_simple(excelType, sheetName, 1, 0, fileOutputStream, headerList, sheetDatas);
            Util.printCurrentTime();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**Purpose:获得列名(需根据业务逻辑重写)
     * @author changle
     * Create Time: 2019年5月21日 
     * @return
     * Version: 1.0
     */
    private ArrayList<String> getSheetColumnName() {
        ArrayList<String> headerList = new ArrayList<String>();
        headerList.add("列名1");
//        headerList.add("列名2");
//        headerList.add("列名3");
        return headerList;
    }
    
    /**
     * Purpose:获得列数据(需要根据业务逻辑重写)
     * @author changle
     * Create Time: 2019年5月21日 
     * @return
     * @throws IOException
     * Version: 1.0
     */
    public static List<List<Object>> getSheetDatas() throws IOException {
        List<List<Object>> datas = new ArrayList<List<Object>>();
        for (int i = 0; i < 1; i++) {
            List<Object> data = new ArrayList<Object>();
            data.add("字符串" + i);
//            data.add(Long.valueOf(187837834l + i));
//            data.add(Integer.valueOf(2233 + i));
            datas.add(data);
        }
        return datas;
    }
   
}
