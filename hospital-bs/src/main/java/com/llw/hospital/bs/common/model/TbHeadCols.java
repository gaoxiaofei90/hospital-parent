package com.llw.hospital.bs.common.model;

public class TbHeadCols {
    private String field;

    private String title;

    public TbHeadCols(String field, String title) {
        this.field = field;
        this.title = title;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
