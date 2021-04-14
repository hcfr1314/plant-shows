package com.hhgs.plantshows.model.DO;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class ComputeIndex {

    private int id;

    //二氧化碳
    private double co2;

    //标准煤
    private double strandardCoal;

    //森林砍伐
    private double deforestation;

    //当日收益
    private double currentEarnings;

    //当月收益
    private double monthEarnings;

    //等效利用小时（月）
    private double monthEquUtilizationHours;

    //等效利用小时(日)
    private double dayEquUtilizationHours;

    //发电效率
    private double powerEficiency;

    //单MW功率
    private double singlMWEficiency;

    //创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

    //场站编码
    private int plantCode;

    //场站名称
    private String plantName;

    public String getPlantName() {
        return plantName;
    }

    public void setPlantName(String plantName) {
        this.plantName = plantName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getPlantCode() {
        return plantCode;
    }

    public void setPlantCode(int plantCode) {
        this.plantCode = plantCode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getCo2() {
        return co2;
    }

    public void setCo2(double co2) {
        this.co2 = co2;
    }

    public double getStrandardCoal() {
        return strandardCoal;
    }

    public void setStrandardCoal(double strandardCoal) {
        this.strandardCoal = strandardCoal;
    }

    public double getDeforestation() {
        return deforestation;
    }

    public void setDeforestation(double deforestation) {
        this.deforestation = deforestation;
    }

    public double getCurrentEarnings() {
        return currentEarnings;
    }

    public void setCurrentEarnings(double currentEarnings) {
        this.currentEarnings = currentEarnings;
    }

    public double getPowerEficiency() {
        return powerEficiency;
    }

    public void setPowerEficiency(double powerEficiency) {
        this.powerEficiency = powerEficiency;
    }

    public double getSinglMWEficiency() {
        return singlMWEficiency;
    }

    public void setSinglMWEficiency(double singlMWEficiency) {
        this.singlMWEficiency = singlMWEficiency;
    }

    public double getMonthEquUtilizationHours() {
        return monthEquUtilizationHours;
    }

    public void setMonthEquUtilizationHours(double monthEquUtilizationHours) {
        this.monthEquUtilizationHours = monthEquUtilizationHours;
    }

    public double getDayEquUtilizationHours() {
        return dayEquUtilizationHours;
    }

    public void setDayEquUtilizationHours(double dayEquUtilizationHours) {
        this.dayEquUtilizationHours = dayEquUtilizationHours;
    }

    public double getMonthEarnings() {
        return monthEarnings;
    }

    public void setMonthEarnings(double monthEarnings) {
        this.monthEarnings = monthEarnings;
    }
}
