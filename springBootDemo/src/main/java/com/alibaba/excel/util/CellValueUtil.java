package com.alibaba.excel.util;

import com.alibaba.excel.annotation.FieldType;
import org.apache.poi.ss.usermodel.BuiltinFormats;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.model.SharedStringsTable;
import org.apache.poi.xssf.model.StylesTable;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.xml.sax.Attributes;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CellValueUtil {
    /**
     * @param attribute
     * @param stylesTable
     * @return
     */
    public static FieldType getCellType(Attributes attribute, StylesTable stylesTable) {
        int numFmtIndex = 0;
        String numFmtString = "";
        FieldType cellDataType = FieldType.of(attribute.getValue("t"));
        // 获取单元格的xf索引，对应style.xml中cellXfs的子元素xf
        final String xfIndexStr = attribute.getValue("s");
        if (xfIndexStr != null) {
            int xfIndex = Integer.parseInt(xfIndexStr);
            XSSFCellStyle xssfCellStyle = stylesTable.getStyleAt(xfIndex);
            numFmtIndex = xssfCellStyle.getDataFormat();
            numFmtString = xssfCellStyle.getDataFormatString();
            if (numFmtString == null) {
                cellDataType = FieldType.NULL;
                numFmtString = BuiltinFormats.getBuiltinFormat(numFmtIndex);
            } else if (org.apache.poi.ss.usermodel.DateUtil.isADateFormat(numFmtIndex, numFmtString)) {
                if(!FieldType.SSTINDEX.equals(cellDataType)) {
                    cellDataType = FieldType.DATE;
                }
            }
        }
        cellDataType.setNumFmtString(numFmtString);
        return cellDataType;
    }

    public static String getCellValue(FieldType cellDataType,
                                      String value,
                                      SharedStringsTable sharedStringsTable,
                                      Boolean use1904windowing,
                                      String dateFormat) {
        if (null == value) {
            return null;
        }

        String result;
        switch (cellDataType) {
            case BOOL:
                result = value;
                break;
            case ERROR:
                result = value;
                break;
            case FORMULA:
                result = value;
                break;
            case INLINESTR:
                result = new XSSFRichTextString(value).toString();
                break;
            case SSTINDEX:
                try {
                    final int index = Integer.parseInt(value);
                    result = new XSSFRichTextString(sharedStringsTable.getEntryAt(index)).getString();
                } catch (NumberFormatException e) {
                    result = value;
                }
                break;
            case NUMBER:
                result = getNumberValue(value, cellDataType.getNumFmtString());
                break;
            case DATE:
                try {
                    result = getDate(value, use1904windowing, dateFormat);
                } catch (Exception e) {
                    result = value;
                }
                break;
            default:
                result = value;
                break;
        }
        return result;

    }

    private static String getDate(String value, Boolean use1904windowing, String dateFormat) {
        if (StringUtils.isEmpty(dateFormat)) {
            return value;
        }
        Date date = DateUtil.getJavaDate(Double.parseDouble(value), use1904windowing);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
        return simpleDateFormat.format(date);
    }


    private static Number getNumber(String value, String numFmtString) {
        if (StringUtils.isEmpty(value)) {
            return null;
        }
        double numValue = Double.parseDouble(value);
        // 普通数字
        if (null != numFmtString && numFmtString.indexOf(".") < 0) {
            final long longPart = (long) numValue;
            if (longPart == numValue) {
                // 对于无小数部分的数字类型，转为Long
                return longPart;
            }
        }
        return numValue;
    }

    private static String getNumberValue(String value, String numFmtString) {
        Number number = getNumber(value, numFmtString);
        if (number != null) {
            return number.toString();
        }
        return null;
    }
}
