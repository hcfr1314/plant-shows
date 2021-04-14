package com.hhgs.plantshows.model.DO;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class IndexData {

    //场站编号
    private int plantCode;

    //当日发电量
    private double currentGeneration;

    //当日上网电量
    private double currentOnGridEnergy;

    //月累计上网电量
    private double monthAccOnGridEnergy;

    //月发电量
    private double monthGeneration;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

    //年发电量
    private double yearGeneration;
    
    //年上网电量
    private double yearAccOnGridEnergy;

    //月累计日照小时数
    private double totalSunShine;

    public double getTotalSunShine() {
        return totalSunShine;
    }

    public void setTotalSunShine(double totalSunShine) {
        this.totalSunShine = totalSunShine;
    }

    public double getMonthGeneration() {
        return monthGeneration;
    }

    public void setMonthGeneration(double monthGeneration) {
        this.monthGeneration = monthGeneration;
    }

    public double getYearGeneration() {
        return yearGeneration;
    }

    public void setYearGeneration(double yearGeneration) {
        this.yearGeneration = yearGeneration;
    }

    public int getPlantCode() {
        return plantCode;
    }

    public void setPlantCode(int plantCode) {
        this.plantCode = plantCode;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public double getCurrentGeneration() {
        return currentGeneration;
    }

    public void setCurrentGeneration(double currentGeneration) {
        this.currentGeneration = currentGeneration;
    }

    public double getCurrentOnGridEnergy() {
        return currentOnGridEnergy;
    }

    public void setCurrentOnGridEnergy(double currentOnGridEnergy) {
        this.currentOnGridEnergy = currentOnGridEnergy;
    }

    public double getMonthAccOnGridEnergy() {
        return monthAccOnGridEnergy;
    }

    public void setMonthAccOnGridEnergy(double monthAccOnGridEnergy) {
        this.monthAccOnGridEnergy = monthAccOnGridEnergy;
    }

    public double getYearAccOnGridEnergy() {
        return yearAccOnGridEnergy;
    }

    public void setYearAccOnGridEnergy(double yearAccOnGridEnergy) {
        this.yearAccOnGridEnergy = yearAccOnGridEnergy;
    }
}
