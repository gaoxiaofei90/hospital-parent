package com.llw.hospital.api.vo.common;

/**
 * 例子
 * {appFieldName:"admissionType",fieldComment:"入院方式",fieldValue:"1",valueComment:"正式入院"}
 * {appFieldName:"admissionType",fieldComment:"入院方式",fieldValue:"0",valueComment:"试住入院"}
 */
public class SysDictResponse {

    private String appFieldName;
    private String fieldComment;
    private String fieldValue;
    private String valueComment;

    public String getAppFieldName() {
        return appFieldName;
    }

    public void setAppFieldName(String appFieldName) {
        this.appFieldName = appFieldName;
    }

    public String getFieldComment() {
        return fieldComment;
    }

    public void setFieldComment(String fieldComment) {
        this.fieldComment = fieldComment;
    }

    public String getFieldValue() {
        return fieldValue;
    }

    public void setFieldValue(String fieldValue) {
        this.fieldValue = fieldValue;
    }

    public String getValueComment() {
        return valueComment;
    }

    public void setValueComment(String valueComment) {
        this.valueComment = valueComment;
    }
}
