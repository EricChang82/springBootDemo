package com.alibaba.excel.util;

import com.alibaba.excel.support.ExcelTypeEnum;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;

import static com.alibaba.excel.util.StyleUtil.buildSheetStyle;

/**
 *
 * @author jipengfei
 */
public class WorkBookUtil {

    public static Workbook createWorkBook(InputStream templateInputStream, ExcelTypeEnum excelType) throws IOException {
        Workbook workbook;
        if (ExcelTypeEnum.XLS.equals(excelType)) {
            workbook = (templateInputStream == null) ? new HSSFWorkbook() : new HSSFWorkbook(
                new POIFSFileSystem(templateInputStream));
        } else {
            workbook = (templateInputStream == null) ? new SXSSFWorkbook(500) : new SXSSFWorkbook(
                new XSSFWorkbook(templateInputStream));
        }
        return workbook;
    }

    public static Sheet createOrGetSheet(Workbook workbook, com.alibaba.excel.metadata.Sheet sheet) {
        Sheet sheet1 = null;
        try {
            try {
                sheet1 = workbook.getSheetAt(sheet.getSheetNo()-1);
            } catch (Exception e) {
            }
            if (null == sheet1) {
                sheet1 = createSheet(workbook, sheet);
                buildSheetStyle(sheet1,sheet.getColumnWidthMap());
            }
        } catch (Exception e) {
            throw new RuntimeException("constructCurrentSheet error", e);
        }
        return sheet1;
    }

    public static Sheet createSheet(Workbook workbook, com.alibaba.excel.metadata.Sheet sheet) {
        return workbook.createSheet(sheet.getSheetName() != null ? sheet.getSheetName() : sheet.getSheetNo() + "");
    }

    public static Row createRow(Sheet sheet, int rowNum) {
        return sheet.createRow(rowNum);
    }

    public static Cell createCell(Row row, int colNum, CellStyle cellStyle, String cellValue) {
        return createCell(row, colNum, cellStyle, cellValue, false);
    }

    public static Cell createCell(Row row, int colNum, CellStyle cellStyle, Object cellValue, Boolean isNum) {
        if (null != cellValue) {
            if (isNum) {
                Cell cell = row.createCell(colNum);
                cell.setCellStyle(cellStyle);
                cell.setCellValue(Double.parseDouble(cellValue.toString()));
                return cell;
            } else if(cellValue instanceof byte[]){
                createImg(row,colNum,(byte[])cellValue);
                return null;
            }else {
                Cell cell = row.createCell(colNum);
                cell.setCellStyle(cellStyle);
                cell.setCellValue(cellValue.toString());
                return cell;
            }
        }else {
            Cell cell = row.createCell(colNum);
            cell.setCellStyle(cellStyle);
            cell.setCellValue("");
            return cell;
        }
    }

    private static void createImg(Row row, int colNum, byte[] pictureData) {

        int n = row.getSheet().getWorkbook().addPicture(pictureData, HSSFWorkbook.PICTURE_TYPE_PNG);
        Drawing drawing = row.getSheet().getDrawingPatriarch();
        if(drawing == null){
            drawing = row.getSheet().createDrawingPatriarch();
        }
        CreationHelper helper = row.getSheet().getWorkbook().getCreationHelper();

        ClientAnchor anchor = helper.createClientAnchor();
        anchor.setDx1(0);
        anchor.setDx2(0);
        anchor.setDy1(0);
        anchor.setDy2(0);
        anchor.setCol1(colNum);
        anchor.setCol2(colNum+1);
        anchor.setRow1(row.getRowNum());
        anchor.setRow2(row.getRowNum()+1);

       // XSSFClientAnchor anchor = new XSSFClientAnchor(0,0,255,255,(short) colNum,row.getRowNum(),(short) colNum+10,row.getRowNum()+10);

        anchor.setAnchorType(ClientAnchor.AnchorType.DONT_MOVE_AND_RESIZE);

        drawing.createPicture(anchor, n);
    }

}
