package com.hhgs.plantshows.model.DO;

/**
 * 汇集站层级对象DEVICEMODEL_COLLECTION_STATION
 */
public class CollectionStation {

    /**
     * 主键id
     */
    private int id;

    /**
     * 场站编号
     */
    private int plantCode;

    /**
     * 系统名称
     */
    private String systemName;

    /**
     * 场站名称
     */
    private String plantName;

    /**
     * 系统kks
     */
    private String systemKks;

    /**
     * 母线名称
     */
    private String busName;

    /**
     * 母线kks
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
     * 连接设备名称
     */
    private String conectDeviceName;

    /**
     * 连接设备kks
     */
    private String conectDeviceKks;

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

    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    public String getPlantName() {
        return plantName;
    }

    public void setPlantName(String plantName) {
        this.plantName = plantName;
    }

    public String getSystemKks() {
        return systemKks;
    }

    public void setSystemKks(String systemKks) {
        this.systemKks = systemKks;
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

    public String getConectDeviceName() {
        return conectDeviceName;
    }

    public void setConectDeviceName(String conectDeviceName) {
        this.conectDeviceName = conectDeviceName;
    }

    public String getConectDeviceKks() {
        return conectDeviceKks;
    }

    public void setConectDeviceKks(String conectDeviceKks) {
        this.conectDeviceKks = conectDeviceKks;
    }
}
