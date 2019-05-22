package com.alibaba.excel.annotation;

/**
 *
 * @author jipengfei
 * @date 2017/03/15
 */
public enum FieldType {

    /** Boolean类型 */
    BOOL("b"),
    /** 类型错误 */
    ERROR("e"),
    /** 计算结果类型 */
    FORMULA("str"),
    /** 富文本类型 */
    INLINESTR("inlineStr"),
    /** 字符串类型 */
    SSTINDEX("s"),
    /** 数字类型 */
    NUMBER(""),
    /** 日期类型 */
    DATE("m/d/yy"),
    /** 空类型 */
    NULL("");

    /** 属性值 */
    private String name;

    private String numFmtString;

    private FieldType(String name) {
        this.name = name;
    }

    public static FieldType of(String name) {
        if(null == name) {
            return NUMBER;
        }
        if(BOOL.name.equals(name)) {
            return BOOL;
        }else if(ERROR.name.equals(name)) {
            return ERROR;
        }else if(INLINESTR.name.equals(name)) {
            return INLINESTR;
        }else if(SSTINDEX.name.equals(name)) {
            return SSTINDEX;
        }else if(FORMULA.name.equals(name)) {
            return FORMULA;
        }else {
            return NULL;
        }
    }

    public String getNumFmtString() {
        return numFmtString;
    }

    public void setNumFmtString(String numFmtString) {
        this.numFmtString = numFmtString;
    }}
