package com.hhgs.plantshows.common;

public enum FileTypeEnum {
    ZIP(".zip"),XLS(".xls"),XLSX(".xlsx");

    private String val;

    private FileTypeEnum() {

    }

    private FileTypeEnum(String val) {
        this.val =val;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }
}
