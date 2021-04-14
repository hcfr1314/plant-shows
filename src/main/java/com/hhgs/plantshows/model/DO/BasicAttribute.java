package com.hhgs.plantshows.model.DO;

/**
 * 基础属性类
 */
public class BasicAttribute {

    /**
     * 属性id
     */
    private int id;

    /**
     * 属性中文名
     */
    private String attributeNameCN;

    /**
     * 属性英文名称
     */
    private String attributeNameEN;

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getEffective() {
        return effective;
    }

    public void setEffective(int effective) {
        this.effective = effective;
    }

    /**
     * 单位
     */
    private String unit;

    /**
     * 属性类型 1，光伏  2，风电
     */
    private int type;

    /**
     * 有效性 1 有效 0 无效
     */
    private int effective;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAttributeNameCN() {
        return attributeNameCN;
    }

    public void setAttributeNameCN(String attributeNameCN) {
        this.attributeNameCN = attributeNameCN;
    }

    public String getAttributeNameEN() {
        return attributeNameEN;
    }

    public void setAttributeNameEN(String attributeNameEN) {
        this.attributeNameEN = attributeNameEN;
    }
}
