package com.hhgs.plantshows.model.UtilModel;

/**
 * 属性和设备的对应关系
 */
public class BasicDevice {
    private int id;
    private String deviceName;
    private int deviceType;
    private int attr_id;
    private int effective;

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

    //测点类型（YX,YC）
    private String pointType;

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

    public int getEffective() {
        return effective;
    }

    public void setEffective(int effective) {
        this.effective = effective;
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

    public int getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(int deviceType) {
        this.deviceType = deviceType;
    }

    public int getAttr_id() {
        return attr_id;
    }

    public void setAttr_id(int attr_id) {
        this.attr_id = attr_id;
    }
}
