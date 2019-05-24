package com.alibaba.easyexcel.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.Test;

import com.alibaba.easyexcel.test.listen.ExcelListenerUDF;
import com.alibaba.easyexcel.test.model.ReadModel;
import com.alibaba.easyexcel.test.model.ReadModel2;
import com.alibaba.easyexcel.test.util.FileUtil;
import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.metadata.Sheet;

import cn.Util;

public class ReadTestUDF {

//    中间或最后多余空行的不会被读取
    /**
     * 07版本excel读数据量大于1千行，内部采用回调方法.
     *
     * @throws IOException 简单抛出异常，真实环境需要catch异常,同时在finally中关闭流
     */
    @Test
    public void saxReadListStringV2007() throws IOException {
        FileInputStream fileInputStream = new FileInputStream("E:\\1工作区\\库位1.xlsx");
        
 

        //读取第一个sheet的内容
        //        readExcel(fileInputStream, excelProcessListener);

        //读取指定sheetNum的内容
        //#1S-传参-S
        HashMap<String, String> paraMapForLineProcess = new HashMap<String, String>();
        paraMapForLineProcess.put("orderNo", "1111");
        //#2E-传参-E 
    
        readSheetOfExcel(fileInputStream,1, new ExcelListenerUDF() {
            int totleNum=0;
            @SuppressWarnings("unchecked")
            @Override
            public void invoke(Object object, AnalysisContext context) {
                ArrayList<String> dataListForRow=((ArrayList<String>)object);
                //#1S-业务逻辑处理-S
                int rowNum =context.getCurrentRowNum();
//                int totleNum =context.getTotalCount().intValue();
                Util.print("获得参数:" + paraMapForLineProcess.get("orderNo"));
                System.out.println("行："+rowNum+"==>"+dataListForRow);
                totleNum++;
                //#2E-业务逻辑处理-E 
            }
            @Override
            public void doAfterAllAnalysed(AnalysisContext context) {
//                int rowNum =context.getCurrentRowNum();
                System.out.println(totleNum);
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
          
            }
        });

        Util.print("读取完成");
    }

    /**Purpose:读取指定sheet的内容
     * @author changle
     * Create Time: 2019年5月23日 
     * @param fileInputStream
     * @param sheetNumToRead TODO
     * @param excelProcessListener
     * Version: 1.0
     */
    private void readSheetOfExcel(FileInputStream fileInputStream, int sheetNumToRead, ExcelListenerUDF excelProcessListener) {
        try {
            ExcelReader excelReader = EasyExcelFactory.getReader(fileInputStream, excelProcessListener);
            List<Sheet> sheets = excelReader.getSheets();
            for (Sheet sheet : sheets) {
                if (sheet.getSheetNo() == sheetNumToRead) {
                    Util.print("sheetNo:"+sheet.getSheetNo() +"startRow:"+sheet.getStartRow());
                    excelReader.read(sheet);
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fileInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**Purpose:读取第一个sheet的内容
     * @author changle
     * Create Time: 2019年5月23日 
     * @param fileInputStream TODO
     * @param excelProcessListener TODO
     * @throws FileNotFoundException
     * @throws IOException
     * Version: 1.0
     */
    private void readExcel(FileInputStream fileInputStream, AnalysisEventListener<Object> excelProcessListener) {
        try {
            EasyExcelFactory.readBySax(fileInputStream, new Sheet(1, 1), excelProcessListener);//XLS 和xlsx都是这个方法
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fileInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 07版本excel读取sheet
     *
     * @throws IOException 简单抛出异常，真实环境需要catch异常,同时在finally中关闭流
     */
    @Test
    public void saxReadSheetsV2007() throws IOException {
        InputStream inputStream = FileUtil.getResourcesFileInputStream("2007.xlsx");
        ExcelListenerUDF ExcelListenerUDF = new ExcelListenerUDF();
        ExcelReader excelReader = EasyExcelFactory.getReader(inputStream, ExcelListenerUDF);
        List<Sheet> sheets = excelReader.getSheets();
        System.out.println("llll****" + sheets);
        System.out.println();
        for (Sheet sheet : sheets) {
            if (sheet.getSheetNo() == 1) {
                excelReader.read(sheet);
            } else if (sheet.getSheetNo() == 2) {
                sheet.setHeadLineMun(1);
                sheet.setClazz(ReadModel.class);
                excelReader.read(sheet);
            } else if (sheet.getSheetNo() == 3) {
                sheet.setHeadLineMun(1);
                sheet.setClazz(ReadModel2.class);
                excelReader.read(sheet);
            }
        }
        inputStream.close();
    }

    /**
     * 03版本excel读数据量大于1千行数据，内部采用回调方法.
     *
     * @throws IOException 简单抛出异常，真实环境需要catch异常,同时在finally中关闭流
     */
    @Test
    public void saxReadListStringV2003() throws IOException {
        InputStream inputStream = FileUtil.getResourcesFileInputStream("2003.xls");
        ExcelListenerUDF ExcelListenerUDF = new ExcelListenerUDF();
        EasyExcelFactory.readBySax(inputStream, new Sheet(2, 1), ExcelListenerUDF);
        inputStream.close();
    }

    /**
     * 00版本excel读取sheet
     *
     * @throws IOException 简单抛出异常，真实环境需要catch异常,同时在finally中关闭流
     */
    @Test
    public void saxReadSheetsV2003() throws IOException {
        InputStream inputStream = FileUtil.getResourcesFileInputStream("2003.xls");
        ExcelListenerUDF ExcelListenerUDF = new ExcelListenerUDF();
        ExcelReader excelReader = EasyExcelFactory.getReader(inputStream, ExcelListenerUDF);
        List<Sheet> sheets = excelReader.getSheets();
        System.out.println();
        for (Sheet sheet : sheets) {
            if (sheet.getSheetNo() == 1) {
                excelReader.read(sheet);
            } else {
                sheet.setHeadLineMun(2);
                sheet.setClazz(ReadModel.class);
                excelReader.read(sheet);
            }
        }
        inputStream.close();
    }

    public void print(List<Object> datas) {
        int i = 0;
        for (Object ob : datas) {
            System.out.println(i++);
            System.out.println(ob);
        }
    }

}
