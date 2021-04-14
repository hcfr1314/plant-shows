package com.hhgs.plantshows.model.DO;

public class SmallsysComponent {

    //组件id
    private int componentId;

    //小系统id
    private int smallsysId;

    //组件名称
    private String componentName;

    //场站名称
    private String plantName;

    public int getComponentId() {
        return componentId;
    }

    public void setComponentId(int componentId) {
        this.componentId = componentId;
    }

    public int getSmallsysId() {
        return smallsysId;
    }

    public void setSmallsysId(int smallsysId) {
        this.smallsysId = smallsysId;
    }

    public String getComponentName() {
        return componentName;
    }

    public void setComponentName(String componentName) {
        this.componentName = componentName;
    }

    public String getPlantName() {
        return plantName;
    }

    public void setPlantName(String plantName) {
        this.plantName = plantName;
    }
}
