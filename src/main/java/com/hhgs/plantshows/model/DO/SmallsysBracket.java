package com.hhgs.plantshows.model.DO;


public class SmallsysBracket {

    //支架id
    private int bracketId;

    //小系统id
    private int smallsysId;

    //支架名称
    private String bracketName;

    //场站名称
    private String plantName;

    public int getBracketId() {
        return bracketId;
    }

    public void setBracketId(int bracketId) {
        this.bracketId = bracketId;
    }

    public int getSmallsysId() {
        return smallsysId;
    }

    public void setSmallsysId(int smallsysId) {
        this.smallsysId = smallsysId;
    }

    public String getBracketName() {
        return bracketName;
    }

    public void setBracketName(String bracketName) {
        this.bracketName = bracketName;
    }

    public String getPlantName() {
        return plantName;
    }

    public void setPlantName(String plantName) {
        this.plantName = plantName;
    }
}
