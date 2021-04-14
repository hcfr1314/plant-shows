package com.hhgs.plantshows.model.DO;


public class SecondsDeviceSign {
    private int deviceSignId;

    private int deviceId;

    private String deviceSignName;

    private String plantName;

    public int getDeviceSignId() {
        return deviceSignId;
    }

    public void setDeviceSignId(int deviceSignId) {
        this.deviceSignId = deviceSignId;
    }

    public int getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceSignName() {
        return deviceSignName;
    }

    public void setDeviceSignName(String deviceSignName) {
        this.deviceSignName = deviceSignName;
    }

    public String getPlantName() {
        return plantName;
    }

    public void setPlantName(String plantName) {
        this.plantName = plantName;
    }
}
