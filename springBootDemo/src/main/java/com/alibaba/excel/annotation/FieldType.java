package com.alibaba.excel.annotation;

/**
 *
 * @author jipengfei
 * @date 2017/03/15
 */
public enum FieldType {

    /** Boolean���� */
    BOOL("b"),
    /** ���ʹ��� */
    ERROR("e"),
    /** ���������� */
    FORMULA("str"),
    /** ���ı����� */
    INLINESTR("inlineStr"),
    /** �ַ������� */
    SSTINDEX("s"),
    /** �������� */
    NUMBER(""),
    /** �������� */
    DATE("m/d/yy"),
    /** ������ */
    NULL("");

    /** ����ֵ */
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
