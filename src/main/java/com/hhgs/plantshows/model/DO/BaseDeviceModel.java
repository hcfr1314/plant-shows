package com.hhgs.plantshows.model.DO;

/**
 * 设备模型基础类
 */
public class BaseDeviceModel {

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
     * 设备类型（1，光伏 2，风电）
     */
    private int deviceType;

    //上限值
    private String upperLimit;

    //下限值
    private String lowerLimit;

    //上上限
    private String moreUpperLimit;

    //下下限
    private String moreLowerLimit;

    //测点类型（YX,YC）
    private String pointType;

    //异常值
    private String exceptionValue;

    public int getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(int deviceType) {
        this.deviceType = deviceType;
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

    public String getPointType() {
        return pointType;
    }

    public void setPointType(String pointType) {
        this.pointType = pointType;
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
