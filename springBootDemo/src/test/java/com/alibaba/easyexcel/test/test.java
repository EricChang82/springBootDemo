package com.alibaba.easyexcel.test;

import com.alibaba.easyexcel.test.model.WriteModel;
import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.metadata.Table;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import static com.alibaba.easyexcel.test.util.DataUtil.*;
import static com.alibaba.easyexcel.test.util.DataUtil.createTestListJavaMode;

public class test {

    public static void main(String[] args) throws IOException {
        OutputStream out = new FileOutputStream("/Users/jipengfei/2007.xlsx");
        ExcelWriter writer = EasyExcelFactory.getWriter(out);
        //д��һ��sheet, sheet1  ����ȫ��List<String> ��ģ��ӳ���ϵ
        Sheet sheet1 = new Sheet(1, 3);
        sheet1.setSheetName("��һ��sheet");

        //�����п� ����ÿ�еĿ��
        Map columnWidth = new HashMap();
        columnWidth.put(0,10000);columnWidth.put(1,40000);columnWidth.put(2,10000);columnWidth.put(3,10000);
        sheet1.setColumnWidthMap(columnWidth);
        sheet1.setHead(createTestListStringHead());
        //or ��������Ӧ���
        //sheet1.setAutoWidth(Boolean.TRUE);
        writer.write1(createTestListObject(), sheet1);

        //д�ڶ���sheet sheet2  ģ���ϴ��б�ͷ��ע�⣬�ϲ���Ԫ��
        Sheet sheet2 = new Sheet(2, 3, WriteModel.class, "�ڶ���sheet", null);
        sheet2.setTableStyle(createTableStyle());
        //writer.write1(null, sheet2);
        writer.write(createTestListJavaMode(), sheet2);
        //��Ҫ�ϲ���Ԫ��
        writer.merge(5,20,1,1);

        //д������sheet�������table���
        Sheet sheet3 = new Sheet(3, 0);
        sheet3.setSheetName("������sheet");
        Table table1 = new Table(1);
        table1.setHead(createTestListStringHead());
        writer.write1(createTestListObject(), sheet3, table1);

        //дsheet2  ģ���ϴ��б�ͷ��ע��
        Table table2 = new Table(2);
        table2.setTableStyle(createTableStyle());
        table2.setClazz(WriteModel.class);
        writer.write(createTestListJavaMode(), sheet3, table2);

        writer.finish();
        out.close();
    }
}
