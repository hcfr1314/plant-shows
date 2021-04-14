package com.hhgs.plantshows.model.DO;

public class PlantTable {

    private String id;

    private String plantName;

    private String plantType;

    //是否已生成层级
    private String isHierarchy;

    //场站编码
    private int plantCode;

    public int getPlantCode() {
        return plantCode;
    }

    public void setPlantCode(int plantCode) {
        this.plantCode = plantCode;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPlantName() {
        return plantName;
    }

    public void setPlantName(String plantName) {
        this.plantName = plantName;
    }

    public String getPlantType() {
        return plantType;
    }

    public void setPlantType(String plantType) {
        this.plantType = plantType;
    }

    public String getIsHierarchy() {
        return isHierarchy;
    }

    public void setIsHierarchy(String isHierarchy) {
        this.isHierarchy = isHierarchy;
    }
}
