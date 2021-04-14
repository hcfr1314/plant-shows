package com.hhgs.plantshows.model.DO;

public class LcuEquipment {
    private int id;

    private String lcuId;

    private String sys;

    private String equipmentName;

    private int plantCode;

    private String plantName;


    public String getSys() {
        return sys;
    }

    public void setSys(String sys) {
        this.sys = sys;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLcuId() {
        return lcuId;
    }

    public void setLcuId(String lcuId) {
        this.lcuId = lcuId;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
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
}
