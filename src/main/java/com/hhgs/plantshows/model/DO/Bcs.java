package com.hhgs.plantshows.model.DO;

public class Bcs {

    //原始测点id
    private String orgId;

    //设备id
    private String lcuId;

    private String BcsCnName;

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getLcuId() {
        return lcuId;
    }

    public void setLcuId(String lcuId) {
        this.lcuId = lcuId;
    }

    @Override
    public String toString() {
        return "Bcs{" +
                "orgId='" + orgId + '\'' +
                ", lcuId='" + lcuId + '\'' +
                '}';
    }
}
