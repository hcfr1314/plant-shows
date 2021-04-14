package com.hhgs.plantshows.model.DO;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;

public class ImportData {

    //大系统名称
    private String bigsysName;

    //小系统名称
    private String smallsysName;

    //一级设备名称
    private String firstsDeviceName;

    //二级设备名称
    private String secondsDeviceName;

    //部件名称
    private String partsName;

    //测点名称
    private String pointName;

    //全局编码
    private String globalNumber;

    //原始测点id
    private String originalPoint;

    //原始测点描述
    private String pointDescript;

    //一级设备标识
    private String firstsDeviceSign;

    //二级设备标识
    private String secondsDeviceSign;

    //支架类型
    private String bracketType;

    //组件类型
    private String componentType;

    //判断测点是否为空
    private String isOrNotNull;

    //大系统列表
    private List<String> bigSysList;

    //设备标识列表
    private List<String> deviceSignList;

    public String getIsOrNotNull() {
        return isOrNotNull;
    }

    public void setIsOrNotNull(String isOrNotNull) {
        this.isOrNotNull = isOrNotNull;
    }

    //导入时间
    //@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    //private Date createTime;



    public ImportData() {
    }

    public List<String> getBigSysList() {
        return bigSysList;
    }

    public void setBigSysList(List<String> bigSysList) {
        this.bigSysList = bigSysList;
    }

    public List<String> getDeviceSignList() {
        return deviceSignList;
    }

    public void setDeviceSignList(List<String> deviceSignList) {
        this.deviceSignList = deviceSignList;
    }

    public String getBigsysName() {
        return bigsysName;
    }

    public void setBigsysName(String bigsysName) {
        this.bigsysName = bigsysName;
    }

    public String getSmallsysName() {
        return smallsysName;
    }

    public void setSmallsysName(String smallsysName) {
        this.smallsysName = smallsysName;
    }

    public String getFirstsDeviceName() {
        return firstsDeviceName;
    }

    public void setFirstsDeviceName(String firstsDeviceName) {
        this.firstsDeviceName = firstsDeviceName;
    }

    public String getSecondsDeviceName() {
        return secondsDeviceName;
    }

    public void setSecondsDeviceName(String secondsDeviceName) {
        this.secondsDeviceName = secondsDeviceName;
    }

    public String getPartsName() {
        return partsName;
    }

    public void setPartsName(String partsName) {
        this.partsName = partsName;
    }

    public String getPointName() {
        return pointName;
    }

    public void setPointName(String pointName) {
        this.pointName = pointName;
    }

    public String getGlobalNumber() {
        return globalNumber;
    }

    public void setGlobalNumber(String globalNumber) {
        this.globalNumber = globalNumber;
    }

    public String getOriginalPoint() {
        return originalPoint;
    }

    public void setOriginalPoint(String originalPoint) {
        this.originalPoint = originalPoint;
    }

    public String getPointDescript() {
        return pointDescript;
    }

    public void setPointDescript(String pointDescript) {
        this.pointDescript = pointDescript;
    }

    public String getFirstsDeviceSign() {
        return firstsDeviceSign;
    }

    public void setFirstsDeviceSign(String firstsDeviceSign) {
        this.firstsDeviceSign = firstsDeviceSign;
    }

    public String getSecondsDeviceSign() {
        return secondsDeviceSign;
    }

    public void setSecondsDeviceSign(String secondsDeviceSign) {
        this.secondsDeviceSign = secondsDeviceSign;
    }

    public String getBracketType() {
        return bracketType;
    }

    public void setBracketType(String bracketType) {
        this.bracketType = bracketType;
    }

    public String getComponentType() {
        return componentType;
    }

    public void setComponentType(String componentType) {
        this.componentType = componentType;
    }
}
