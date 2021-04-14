package com.hhgs.plantshows.model.DO;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class PlantInfo {

    private int id;

    //场站名称
    private String plantName;

    //场站编码
    private int plantCode;

    //场站类型
    private String plantType;

    //装机容量
    private double installCapacity;

    //组串跟踪方式
    private String stringTrackingMode;

    //占地总面积
    private String totalFloorArea;

    //接入时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date insertTime;


    //计划运行年限
    private String plannedOperationPeriod;

    //逆变器类型
    private String inverterType;

    //地势海拔
    private String attitude;

    //组串数量
    private int numberOfStrings;

    //逆变器数量
    private int numberOfInverters;

    //相变数量
    private int numberOfPhaTransitions;

    //汇流箱数量
    private int numberOfComBoxes;

    //站点
    private String sites;

    //电站所在地
    private String locationPowerStation;

    //GIS坐标
    private String gisCorrdinate;

    //安装倾角
    private String dipAngle;

    public String getDipAngle() {
        return dipAngle;
    }

    public void setDipAngle(String dipAngle) {
        this.dipAngle = dipAngle;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlantName() {
        return plantName;
    }

    public void setPlantName(String plantName) {
        this.plantName = plantName;
    }

    public int getPlantCode() {
        return plantCode;
    }

    public void setPlantCode(int plantCode) {
        this.plantCode = plantCode;
    }

    public String getPlantType() {
        return plantType;
    }

    public void setPlantType(String plantType) {
        this.plantType = plantType;
    }

    public double getInstallCapacity() {
        return installCapacity;
    }

    public void setInstallCapacity(double installCapacity) {
        this.installCapacity = installCapacity;
    }

    public String getStringTrackingMode() {
        return stringTrackingMode;
    }

    public void setStringTrackingMode(String stringTrackingMode) {
        this.stringTrackingMode = stringTrackingMode;
    }

    public String getTotalFloorArea() {
        return totalFloorArea;
    }

    public void setTotalFloorArea(String totalFloorArea) {
        this.totalFloorArea = totalFloorArea;
    }

    public Date getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }

    public String getPlannedOperationPeriod() {
        return plannedOperationPeriod;
    }

    public void setPlannedOperationPeriod(String plannedOperationPeriod) {
        this.plannedOperationPeriod = plannedOperationPeriod;
    }

    public String getInverterType() {
        return inverterType;
    }

    public void setInverterType(String inverterType) {
        this.inverterType = inverterType;
    }

    public String getAttitude() {
        return attitude;
    }

    public void setAttitude(String attitude) {
        this.attitude = attitude;
    }

    public String getSites() {
        return sites;
    }

    public void setSites(String sites) {
        this.sites = sites;
    }

    public String getLocationPowerStation() {
        return locationPowerStation;
    }

    public void setLocationPowerStation(String locationPowerStation) {
        this.locationPowerStation = locationPowerStation;
    }

    public String getGisCorrdinate() {
        return gisCorrdinate;
    }

    public void setGisCorrdinate(String gisCorrdinate) {
        this.gisCorrdinate = gisCorrdinate;
    }

    public int getNumberOfStrings() {
        return numberOfStrings;
    }

    public void setNumberOfStrings(int numberOfStrings) {
        this.numberOfStrings = numberOfStrings;
    }

    public int getNumberOfInverters() {
        return numberOfInverters;
    }

    public void setNumberOfInverters(int numberOfInverters) {
        this.numberOfInverters = numberOfInverters;
    }

    public int getNumberOfPhaTransitions() {
        return numberOfPhaTransitions;
    }

    public void setNumberOfPhaTransitions(int numberOfPhaTransitions) {
        this.numberOfPhaTransitions = numberOfPhaTransitions;
    }

    public int getNumberOfComBoxes() {
        return numberOfComBoxes;
    }

    public void setNumberOfComBoxes(int numberOfComBoxes) {
        this.numberOfComBoxes = numberOfComBoxes;
    }
}
