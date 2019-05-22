package com.alibaba.easyexcel.test.listen;

import com.alibaba.excel.event.WriteHandler;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

public class AfterWriteHandlerImpl implements WriteHandler {

    @Override
    public void sheet(int sheetNo, Sheet sheet) {
         sheet.getWorkbook();
    }

    @Override
    public void row(int rowNum, Row row) {
        if(row == null){
            return;
        }
        row.getSheet().getWorkbook();

    }
    @Override
    public void cell(int cellNum, Cell cell) {
        if(cell == null){
            System.out.println("....");
            return;
        }
        Workbook workbook = cell.getSheet().getWorkbook();
        Sheet currentSheet = cell.getSheet();
        if (cellNum == 4 && cell.getRowIndex() == 30) {
            Drawing draw = currentSheet.createDrawingPatriarch();
            Comment comment = draw.createCellComment(new XSSFClientAnchor(0, 0, 0, 0, 4, 25, 9, 30));
            XSSFRichTextString rtf = new XSSFRichTextString("添加批注内容收到货死的死哦多胡搜idsad是否会杜甫的范德萨发！1111");
            Font commentFormatter = workbook.createFont();
            commentFormatter.setFontName("宋体");
            //设置字体大小
            commentFormatter.setFontHeightInPoints((short)9);
            rtf.applyFont(commentFormatter);
            comment.setString(rtf);
            comment.setAuthor("ceshi");
            cell.setCellComment(comment);
        }
    }
}
