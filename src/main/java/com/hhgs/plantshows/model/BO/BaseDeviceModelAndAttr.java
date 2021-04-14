package com.hhgs.plantshows.model.BO;

/**
 * 设备模型基础类
 */
public class BaseDeviceModelAndAttr {

    /**
     * 主键id
     */
    private int id;

    /**
     * 设备名称
     */
    private String deviceName;

    /**
     * 有效性 1 有效 0 无效
     */
    private int effective;

    /**
     * 外键，关联具体属性
     */
    private  int attributeID;

    /**
     * 属性中文名
     */
    private String attributeNameCN;

    /**
     * 属性英文名称
     */
    private String attributeNameEN;

    /**
     * 设备类型（1光伏 ，2风电）
     */
    private int deviceType;

    /**
     * 单位
     */
    private String unit;

    /**
     * 特征值
     */
    private String eigenValue;

    /**
     * 上限
     */
    private String upperLimit;

    /**
     * 下限
     */
    private String lowerLimit;

    //上上限
    private String moreUpperLimit;

    //下下限
    private String moreLowerLimit;


    //测点类型(YC,YX)
    private String pointType;

    //异常值
    private String exceptionValue;

    public String getPointType() {
        return pointType;
    }

    public void setPointType(String pointType) {
        this.pointType = pointType;
    }

    public String getEigenValue() {
        return eigenValue;
    }

    public void setEigenValue(String eigenValue) {
        this.eigenValue = eigenValue;
    }

    public String getUpperLimit() {
        return upperLimit;
    }

    public void setUpperLimit(String upperLimit) {
        this.upperLimit = upperLimit;
    }

    public String getLowerLimit() {
        return lowerLimit;
    }

    public void setLowerLimit(String lowerLimit) {
        this.lowerLimit = lowerLimit;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(int deviceType) {
        this.deviceType = deviceType;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public int getEffective() {
        return effective;
    }

    public void setEffective(int effective) {
        this.effective = effective;
    }

    public int getAttributeID() {
        return attributeID;
    }

    public void setAttributeID(int attributeID) {
        this.attributeID = attributeID;
    }

    public String getMoreUpperLimit() {
        return moreUpperLimit;
    }

    public void setMoreUpperLimit(String moreUpperLimit) {
        this.moreUpperLimit = moreUpperLimit;
    }

    public String getMoreLowerLimit() {
        return moreLowerLimit;
    }

    public void setMoreLowerLimit(String moreLowerLimit) {
        this.moreLowerLimit = moreLowerLimit;
    }

    public String getExceptionValue() {
        return exceptionValue;
    }

    public void setExceptionValue(String exceptionValue) {
        this.exceptionValue = exceptionValue;
    }
}
