package com.hhgs.plantshows.model.DO;

/**
 * 升压站层级对象DEVICEMODEL_BOOSTER_STATION
 */
public class BootsterStation {

    /**
     * 主键id
     */
    private int id;

    /**
     * 场站编号
     */
    private int plantCode;

    /**
     * 场站名称
     */
    private String plantName;

    /**
     * 主变设备名称
     */
    private String deviceName;

    /**
     * 主变设备kks
     */
    private String deviceKks;

    /**
     * 主变开关柜名称
     */
    private String mainSwitchName;

    /**
     * 主变开关柜kks
     */
    private String mainSwitchKks;

    /**
     * 母线名称
     */
    private String busName;

    /**
     * 母线设备kks
     */
    private String busKks;

    /**
     * 母线开关柜名称
     */
    private String lineSwitchName;

    /**
     * 母线开关柜kks
     */
    private String lineSwitchKks;

    /**
     * 下级设备名称
     */
    private String lowerDeviceName;

    /**
     * 下级设备kks
     */
    private String lowerDeviceKKs;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPlantCode() {
        return plantCode;
    }

    public void setPlantCode(int plantCode) {
        this.plantCode = plantCode;
    }

    public String getPlantName() {
        return plantName;
    }

    public void setPlantName(String plantName) {
        this.plantName = plantName;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getDeviceKks() {
        return deviceKks;
    }

    public void setDeviceKks(String deviceKks) {
        this.deviceKks = deviceKks;
    }

    public String getMainSwitchName() {
        return mainSwitchName;
    }

    public void setMainSwitchName(String mainSwitchName) {
        this.mainSwitchName = mainSwitchName;
    }

    public String getMainSwitchKks() {
        return mainSwitchKks;
    }

    public void setMainSwitchKks(String mainSwitchKks) {
        this.mainSwitchKks = mainSwitchKks;
    }

    public String getBusName() {
        return busName;
    }

    public void setBusName(String busName) {
        this.busName = busName;
    }

    public String getBusKks() {
        return busKks;
    }

    public void setBusKks(String busKks) {
        this.busKks = busKks;
    }

    public String getLineSwitchName() {
        return lineSwitchName;
    }

    public void setLineSwitchName(String lineSwitchName) {
        this.lineSwitchName = lineSwitchName;
    }

    public String getLineSwitchKks() {
        return lineSwitchKks;
    }

    public void setLineSwitchKks(String lineSwitchKks) {
        this.lineSwitchKks = lineSwitchKks;
    }

    public String getLowerDeviceName() {
        return lowerDeviceName;
    }

    public void setLowerDeviceName(String lowerDeviceName) {
        this.lowerDeviceName = lowerDeviceName;
    }

    public String getLowerDeviceKKs() {
        return lowerDeviceKKs;
    }

    public void setLowerDeviceKKs(String lowerDeviceKKs) {
        this.lowerDeviceKKs = lowerDeviceKKs;
    }
}
