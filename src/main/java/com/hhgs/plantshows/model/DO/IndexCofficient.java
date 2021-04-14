package com.hhgs.plantshows.model.DO;

public class IndexCofficient {

    //收益系数
    private double incomeCofficient;

    //煤系数
    private double coalCofficient;

    //co2系数
    private double co2Cofficient;

    //森林砍伐系数
    private double deforstation;

    public double getDeforstation() {
        return deforstation;
    }

    public void setDeforstation(double deforstation) {
        this.deforstation = deforstation;
    }

    public double getIncomeCofficient() {
        return incomeCofficient;
    }

    public void setIncomeCofficient(double incomeCofficient) {
        this.incomeCofficient = incomeCofficient;
    }

    public double getCoalCofficient() {
        return coalCofficient;
    }

    public void setCoalCofficient(double coalCofficient) {
        this.coalCofficient = coalCofficient;
    }

    public double getCo2Cofficient() {
        return co2Cofficient;
    }

    public void setCo2Cofficient(double co2Cofficient) {
        this.co2Cofficient = co2Cofficient;
    }
}
