package com.hhgs.plantshows.model.DO;

public class IrradiationIndex {

    //当日辐照量
    private double currentIrradition;

    //当月累计辐照量
    private double monthAccIrradition;

    //日着小时当月累计值
    private double totalSunShineOfMonth;

    //日照小时当日值
    private double totalSunShineOfDay;

    //场站编码
    private int plantCode;

    public int getPlantCode() {
        return plantCode;
    }

    public void setPlantCode(int plantCode) {
        this.plantCode = plantCode;
    }

    public double getTotalSunShineOfMonth() {
        return totalSunShineOfMonth;
    }

    public void setTotalSunShineOfMonth(double totalSunShineOfMonth) {
        this.totalSunShineOfMonth = totalSunShineOfMonth;
    }

    public double getTotalSunShineOfDay() {
        return totalSunShineOfDay;
    }

    public void setTotalSunShineOfDay(double totalSunShineOfDay) {
        this.totalSunShineOfDay = totalSunShineOfDay;
    }

    public double getCurrentIrradition() {
        return currentIrradition;
    }

    public void setCurrentIrradition(double currentIrradition) {
        this.currentIrradition = currentIrradition;
    }

    public double getMonthAccIrradition() {
        return monthAccIrradition;
    }

    public void setMonthAccIrradition(double monthAccIrradition) {
        this.monthAccIrradition = monthAccIrradition;
    }
}
