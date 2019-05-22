package com.alibaba.easyexcel.test;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.IndexedColors;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Font;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.metadata.TableStyle;
import com.alibaba.excel.support.ExcelTypeEnum;

public class EasyExcelHelper {

    /**Purpose:获得excel列名称
     * @author changle
     * Create Time: 2019年5月21日 
     * @return
     * Version: 1.0
     */
    public static List<List<String>> getSimpleColumnNameForExcel(List<String> headerNameList) {
        List<List<String>> head = new ArrayList<List<String>>();
        for (int i = 0; i < headerNameList.size(); i++) {
            List<String> headCoulumn = new ArrayList<String>();
            String headerName = headerNameList.get(i);
            String[] headerNames = StringUtils.splitByWholeSeparator(headerName, ",");
            for (int j = 0; j < headerNames.length; j++) {
                String value = headerNames[j];
                headCoulumn.add(value);
            }
            head.add(headCoulumn);
        }
        return head;
    }

    /**Purpose:导出到excle(简单结构的)
     * @author changle
     * Create Time: 2019年5月21日 
     * @param excelType TODO
     * @param sheetName TODO
     * @param sheetNo TODO
     * @param headLineMun TODO
     * @param fileOutputStream TODO
     * @param dataList TODO
     * @param head TODO
     * @throws FileNotFoundException
     * @throws IOException
     * Version: 1.0
     */
    public static void expXLSX_simple(ExcelTypeEnum excelType, String sheetName, int sheetNo, int headLineMun, OutputStream fileOutputStream, List<String> headerList, List<List<Object>> dataList) throws FileNotFoundException, IOException {
        OutputStream out = fileOutputStream;
        List<List<String>> sheetHeaders = getSimpleColumnNameForExcel(headerList);//获得列名list
        ExcelWriter writer =EasyExcelFactory.getWriter(out, excelType,true);

        Sheet sheet1 = new Sheet(sheetNo, headLineMun);
        sheet1.setSheetName(sheetName);
//        sheet1.setTableStyle(createTableStyle());
        sheet1.setAutoWidth(Boolean.TRUE);//设置自适应宽度
        sheet1.setHead(sheetHeaders);
        writer.write1(dataList, sheet1);
        writer.finish();
        out.close();
    }
    public static TableStyle createTableStyle() {
        TableStyle tableStyle = new TableStyle();
        Font headFont = new Font();
        headFont.setBold(true);
        headFont.setFontHeightInPoints((short)22);
        headFont.setFontName("楷体");
        tableStyle.setTableHeadFont(headFont);
        tableStyle.setTableHeadBackGroundColor(IndexedColors.BLUE);

        Font contentFont = new Font();
        contentFont.setBold(false);
        contentFont.setFontHeightInPoints((short)22);
        contentFont.setFontName("黑体");
        tableStyle.setTableContentFont(contentFont);
//        tableStyle.setTableContentBackGroundColor(IndexedColors.GREEN);


        return tableStyle;
    }
}
