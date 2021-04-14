package com.hhgs.plantshows.model.UtilModel;

/**
 * 基础属性类
 */
public class BasicAttributeModel {

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

    /**
     * 类型（1光伏 2，风电）
     */
    private int type;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

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
