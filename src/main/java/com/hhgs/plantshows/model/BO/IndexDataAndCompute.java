package com.hhgs.plantshows.model.BO;

import java.util.Map;

public class IndexDataAndCompute {

    //当日发电量
    private double currentGeneration;

    //当日上网电量
    private double currentOnGridEnergy;

    //月累计上网电量
    private double monthAccOnGridEnergy;

    //年累计上网电量
    private double yearAccOnGridEnergy;

    //二氧化碳值
    private double co2Value;

    //标准煤值
    private double strandCoalValue;

    //森林砍伐
    private double deforestation;

    //当日收益
    private double currentEarnings;

    //当月收益
    private double monthEarnings;

    //等效利用小时数(月)
    private double monthEquUtilizationHours;

    //等效利用小时数(日)
    private double dayEquUtilizationHours;

    //当日辐照量
    private double currentIrradition;

    //实时数据查询结果
    private Map<String,String[]> realTimeDataMap;

    public double getDeforestation() {
        return deforestation;
    }

    public void setDeforestation(double deforestation) {
        this.deforestation = deforestation;
    }

    public Map<String, String[]> getRealTimeDataMap() {
        return realTimeDataMap;
    }

    public void setRealTimeDataMap(Map<String, String[]> realTimeDataMap) {
        this.realTimeDataMap = realTimeDataMap;
    }

    public double getCurrentIrradition() {
        return currentIrradition;
    }

    public void setCurrentIrradition(double currentIrradition) {
        this.currentIrradition = currentIrradition;
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

    public double getCo2Value() {
        return co2Value;
    }

    public void setCo2Value(double co2Value) {
        this.co2Value = co2Value;
    }

    public double getStrandCoalValue() {
        return strandCoalValue;
    }

    public void setStrandCoalValue(double strandCoalValue) {
        this.strandCoalValue = strandCoalValue;
    }

    public double getCurrentEarnings() {
        return currentEarnings;
    }

    public void setCurrentEarnings(double currentEarnings) {
        this.currentEarnings = currentEarnings;
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

    public double getYearAccOnGridEnergy() {
        return yearAccOnGridEnergy;
    }

    public void setYearAccOnGridEnergy(double yearAccOnGridEnergy) {
        this.yearAccOnGridEnergy = yearAccOnGridEnergy;
    }

    @Override
    public String toString() {
        return "IndexDataAndCompute{" +
                "currentGeneration=" + currentGeneration +
                ", currentOnGridEnergy=" + currentOnGridEnergy +
                ", monthAccOnGridEnergy=" + monthAccOnGridEnergy +
                ", yearAccOnGridEnergy=" + yearAccOnGridEnergy +
                ", co2Value=" + co2Value +
                ", strandCoalValue=" + strandCoalValue +
                ", deforestation=" + deforestation +
                ", currentEarnings=" + currentEarnings +
                ", monthEarnings=" + monthEarnings +
                ", monthEquUtilizationHours=" + monthEquUtilizationHours +
                ", dayEquUtilizationHours=" + dayEquUtilizationHours +
                ", currentIrradition=" + currentIrradition +
                ", realTimeDataMap=" + realTimeDataMap +
                '}';
    }
}
