package com.hhgs.plantshows.model.DO;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * 黄河月累计上网电量和月计划发电量对象
 */
public class NetAndPlanPower {

    /**
     * 主键
     */
    private int id;

    /**
     * 黄河公司光伏月累计上网电量
     */
    private double netPower;

    /**
     * 黄河公司光伏月计划发电量
     */
    private double planPower;

    /**
     * 日期
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getNetPower() {
        return netPower;
    }

    public void setNetPower(double netPower) {
        this.netPower = netPower;
    }

    public double getPlanPower() {
        return planPower;
    }

    public void setPlanPower(double planPower) {
        this.planPower = planPower;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
