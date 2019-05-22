package com.alibaba.excel;

import com.alibaba.excel.context.WriteContext;
import com.alibaba.excel.event.WriteHandler;
import com.alibaba.excel.exception.ExcelGenerateException;
import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.excel.metadata.ExcelColumnProperty;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.metadata.Table;
import com.alibaba.excel.parameter.GenerateParam;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.util.CollectionUtils;
import com.alibaba.excel.util.POITempFile;
import com.alibaba.excel.util.TypeUtil;
import com.alibaba.excel.util.WorkBookUtil;
import net.sf.cglib.beans.BeanMap;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

/**
 * Excel Writer This tool is used to write data out to Excel via POI.
 * This object can perform the following two functions.
 * <pre>
 *    1. Create a new empty Excel workbook, write the data to the stream after the data is filled.
 *    2. Edit existing Excel, write the original Excel file, or write it to other places.{@link ExcelWriter(InputStream , OutputStream , ExcelTypeEnum , boolean )}
 * </pre>
 *
 * @author jipengfei
 */
public class ExcelWriter extends WriteContext {


    /**
     * Create new writer
     *
     * @param outputStream the java OutputStream you wish to write the data to
     * @param typeEnum     03 or 07
     */
    public ExcelWriter(OutputStream outputStream, ExcelTypeEnum typeEnum) {
        this(outputStream, typeEnum, true);
    }

    @Deprecated
    private Class<? extends BaseRowModel> objectClass;

    /**
     * @param generateParam
     * @since easyexcel 0.0.1  Use {@link new ExcelWrite(int, int, int)
     */
    @Deprecated
    public ExcelWriter(GenerateParam generateParam) {
        this(generateParam.getOutputStream(), generateParam.getType(), true);
        this.objectClass = generateParam.getClazz();
    }

    /**
     * Create new writer
     *
     * @param outputStream the java OutputStream you wish to write the data to
     * @param typeEnum     03 or 07
     * @param needHead     Do you need to write the header to the file?
     */
    public ExcelWriter(OutputStream outputStream, ExcelTypeEnum typeEnum, boolean needHead) {
        POITempFile.createPOIFilesDirectory();
        this.init(null, outputStream, typeEnum, needHead, null);
    }

    /**
     * Create new writer
     *
     * @param templateInputStream Append data after a POI file ,Can be null（the template POI filesystem that contains the Workbook stream)
     * @param outputStream        the java OutputStream you wish to write the data to
     * @param typeEnum            03 or 07
     */
    public ExcelWriter(InputStream templateInputStream, OutputStream outputStream, ExcelTypeEnum typeEnum, Boolean needHead) {
        POITempFile.createPOIFilesDirectory();
        this.init(templateInputStream, outputStream, typeEnum, needHead, null);
    }


    /**
     * Create new writer
     *
     * @param templateInputStream Append data after a POI file ,Can be null（the template POI filesystem that contains the Workbook stream)
     * @param outputStream        the java OutputStream you wish to write the data to
     * @param typeEnum            03 or 07
     * @param writeHandler        User-defined callback
     */
    public ExcelWriter(InputStream templateInputStream, OutputStream outputStream, ExcelTypeEnum typeEnum, Boolean needHead,
                       WriteHandler writeHandler) {
        POITempFile.createPOIFilesDirectory();
        this.init(templateInputStream, outputStream, typeEnum, needHead, writeHandler);
    }

    /**
     * Write data(List<? extends BaseRowModel>) to a sheet
     *
     * @param data  Data to be written
     * @param sheet Write to this sheet
     * @return this current writer
     */
    public ExcelWriter write(List<? extends BaseRowModel> data, Sheet sheet) {
        this.currentSheet(sheet);
        addContent(data, sheet.getStartRow(), null);
        return this;
    }


    /**
     * Write data to a sheet
     *
     * @param data Data to be written
     * @return this current writer
     */
    @Deprecated
    public ExcelWriter write(List data) {
        if (objectClass != null) {
            return this.write(data, new Sheet(1, 0, objectClass));
        } else {
            return this.write0(data, new Sheet(1, 0, objectClass));

        }
    }

    /**
     * Write data(List<List<String>>) to a sheet
     *
     * @param data  Data to be written
     * @param sheet Write to this sheet
     * @return this
     */
    public ExcelWriter write1(List<List<Object>> data, Sheet sheet) {
        this.currentSheet(sheet);
        addContent(data, sheet.getStartRow(), null);
        return this;
    }

    /**
     * Write data(List<List<String>>) to a sheet
     *
     * @param data  Data to be written
     * @param sheet Write to this sheet
     * @return this
     */
    public ExcelWriter write0(List<List<String>> data, Sheet sheet) {
        this.currentSheet(sheet);
        addContent(data, sheet.getStartRow(), null);
        return this;
    }

    /**
     * Write data(List<? extends BaseRowModel>) to a sheet
     *
     * @param data  Data to be written
     * @param sheet Write to this sheet
     * @param table Write to this table
     * @return this
     */
    public ExcelWriter write(List<? extends BaseRowModel> data, Sheet sheet, Table table) {
        this.currentSheet(sheet);
        this.currentTable(table);
        addContent(data, sheet.getStartRow(), null);
        return this;
    }

    /**
     * Write data(List<List<String>>) to a sheet
     *
     * @param data  Data to be written
     * @param sheet Write to this sheet
     * @param table Write to this table
     * @return this
     */
    public ExcelWriter write0(List<List<String>> data, Sheet sheet, Table table) {
        this.currentSheet(sheet);
        this.currentTable(table);
        addContent(data, sheet.getStartRow(), null);
        return this;
    }

    /**
     * Merge Cells，Indexes are zero-based.
     *
     * @param firstRow Index of first row
     * @param lastRow  Index of last row (inclusive), must be equal to or larger than {@code firstRow}
     * @param firstCol Index of first column
     * @param lastCol  Index of last column (inclusive), must be equal to or larger than {@code firstCol}
     */
    public ExcelWriter merge(int firstRow, int lastRow, int firstCol, int lastCol) {
        CellRangeAddress cra = new CellRangeAddress(firstRow, lastRow, firstCol, lastCol);
        this.getCurrentSheet().addMergedRegion(cra);
        return this;
    }

    /**
     * Write data(List<List<Object>>) to a sheet
     *
     * @param data  Data to be written
     * @param sheet Write to this sheet
     * @param table Write to this table
     * @return
     */
    public ExcelWriter write1(List<List<Object>> data, Sheet sheet, Table table) {
        this.currentSheet(sheet);
        if (table != null) {
            this.currentTable(table);
        }
        addContent(data, sheet.getStartRow(), null);
        return this;
    }

    /**
     * Write data(List<Object>) to a sheet
     *
     * @param data         Data to be written
     * @param sheet        Write to this sheet
     * @param table        Write to this table
     * @param cellStyleMap Per column style
     * @return
     */
    public ExcelWriter write1(List<List<Object>> data, Sheet sheet, Table table, Map<Integer, CellStyle> cellStyleMap) {
        this.currentSheet(sheet);
        if (table != null) {
            this.currentTable(table);
        }
        addContent(data, sheet.getStartRow(), cellStyleMap);
        return this;
    }

    /**
     * Close IO
     */
    public void finish() {
        try {
            this.getWorkbook().write(this.getOutputStream());
            this.getWorkbook().close();
        } catch (IOException e) {
            throw new ExcelGenerateException("IO error", e);
        }
    }

    private void addContent(List data, int startRow, Map<Integer, CellStyle> cellStyleMap) {
        if (CollectionUtils.isEmpty(data)) {
            return;
        }
        int rowNum = this.getCurrentSheet().getLastRowNum();
        if (rowNum == 0) {
            Row row = this.getCurrentSheet().getRow(0);
            if (row == null) {
                if (this.getExcelHeadProperty() == null || !this.needHead()) {
                    rowNum = -1;
                }
            }
        }
        if ((rowNum < startRow) && startRow > 0) {
            rowNum = startRow;
        }
        for (int i = 0; i < data.size(); i++) {
            int n = i + rowNum + 1;
            addOneRowOfDataToExcel(data.get(i), n, cellStyleMap);
        }
    }

    private void addOneRowOfDataToExcel(Object oneRowData, int n, Map<Integer, CellStyle> cellStyleMap) {
        Row row = WorkBookUtil.createRow(this.getCurrentSheet(), n);
        if (null != this.getAfterWriteHandler()) {
            this.getAfterWriteHandler().row(n, row);
        }
        if (oneRowData instanceof List) {
            addBasicTypeToExcel((List) oneRowData, row, cellStyleMap);
        } else {
            addJavaObjectToExcel(oneRowData, row);
        }
    }

    private void addBasicTypeToExcel(List<Object> oneRowData, Row row, Map<Integer, CellStyle> cellStyleMap) {
        if (CollectionUtils.isEmpty(oneRowData)) {
            return;
        }
        for (int i = 0; i < oneRowData.size(); i++) {
            Object cellValue = oneRowData.get(i);
            CellStyle cellStyle = cellStyleMap == null ? this.getCurrentContentStyle()
                    : cellStyleMap.get(i) == null ? this.getCurrentContentStyle() : cellStyleMap.get(i);

            Cell cell = WorkBookUtil.createCell(row, i, cellStyle, cellValue,
                    TypeUtil.isNum(cellValue));
            if (null != this.getAfterWriteHandler()) {
                this.getAfterWriteHandler().cell(i, cell);
            }
        }
    }

    private void addJavaObjectToExcel(Object oneRowData, Row row) {
        int i = 0;
        BeanMap beanMap = BeanMap.create(oneRowData);
        Object value = beanMap.get("rowHeight");
        if (value != null) {
            short height = Short.valueOf(value.toString());
            if (height > 0) {
                row.setHeight(height);
            }
        }
        for (ExcelColumnProperty excelHeadProperty : this.getExcelHeadProperty().getColumnPropertyList()) {
            BaseRowModel baseRowModel = (BaseRowModel) oneRowData;
            Object cellValue = TypeUtil.getFieldValue(beanMap, excelHeadProperty.getField().getName(),
                    excelHeadProperty.getFormat());
            CellStyle cellStyle = baseRowModel.getStyle(i) != null ? baseRowModel.getStyle(i)
                    : this.getCurrentContentStyle();
            Cell cell = WorkBookUtil.createCell(row, i, cellStyle, cellValue,
                    TypeUtil.isNum(excelHeadProperty.getField()));
            if (null != this.getAfterWriteHandler()) {
                this.getAfterWriteHandler().cell(i, cell);
            }
            i++;
        }

    }

}
