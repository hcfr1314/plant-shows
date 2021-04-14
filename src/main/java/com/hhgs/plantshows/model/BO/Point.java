package com.hhgs.plantshows.model.BO;

/**
 * 实时测点数据
 */
public class Point {

    /**
     * 原始测点
     */
    private long orgId;

    /**
     * 场站编号
     */
    private int plantCode;

    /**
     * 测点对应描述
     */
    private String description;

    /**
     * 测点数据类型（1.有功功率，2 平均风速， 3 温度）
     */
    private int orgType;

    /**
     * 场站名称
     */
    private String plantName;

    /**
     * influxDb地址
     */
    private String url;

    /**
     * influxdb表名
     */
    private String dbName;

    /**
     * 原始测点值
     */
    private double value;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getPlantName() {
        return plantName;
    }

    public void setPlantName(String plantName) {
        this.plantName = plantName;
    }

    public long getOrgId() {
        return orgId;
    }

    public void setOrgId(long orgId) {
        this.orgId = orgId;
    }

    public int getPlantCode() {
        return plantCode;
    }

    public void setPlantCode(int plantCode) {
        this.plantCode = plantCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getOrgType() {
        return orgType;
    }

    public void setOrgType(int orgType) {

        this.orgType = orgType;
    }

    @Override
    public String toString() {
        return "Point{" +
                "orgId=" + orgId +
                ", plantCode=" + plantCode +
                ", description='" + description + '\'' +
                ", orgType=" + orgType +
                ", plantName='" + plantName + '\'' +
                ", url='" + url + '\'' +
                ", dbName='" + dbName + '\'' +
                ", value=" + value +
                '}';
    }
}
