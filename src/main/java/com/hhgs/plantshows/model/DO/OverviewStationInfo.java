package com.hhgs.plantshows.model.DO;

/**
 * 首页电站运行状态实体类
 */
public class OverviewStationInfo {

    /**
     * 主键
     */
    private int id;

    /**
     * 地面式
     */
    private int groudType;

    /**
     * 分布式
     */
    private int distritubed;

    /**
     * 并网运行数量
     */
    private int mergeNet;

    /**
     * 故障解列数量
     */
    private int breakDown;

    /**
     * 通讯中断数量
     */
    private int reportInterrupted;

    /**
     * 安全运行天数
     */
    private int runningDays;

    /**
     * 总装机容量
     */
    private double totalInstallCap;

    /**
     * 已并网电站
     */
    private int alreayMerge;

    /**
     * 在建电站
     */
    private int underConstruction;

    /**
     * 并购中电站
     */
    private int mergeBuy;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGroudType() {
        return groudType;
    }

    public void setGroudType(int groudType) {
        this.groudType = groudType;
    }

    public int getDistritubed() {
        return distritubed;
    }

    public void setDistritubed(int distritubed) {
        this.distritubed = distritubed;
    }

    public int getMergeNet() {
        return mergeNet;
    }

    public void setMergeNet(int mergeNet) {
        this.mergeNet = mergeNet;
    }

    public int getBreakDown() {
        return breakDown;
    }

    public void setBreakDown(int breakDown) {
        this.breakDown = breakDown;
    }

    public int getReportInterrupted() {
        return reportInterrupted;
    }

    public void setReportInterrupted(int reportInterrupted) {
        this.reportInterrupted = reportInterrupted;
    }

    public int getRunningDays() {
        return runningDays;
    }

    public void setRunningDays(int runningDays) {
        this.runningDays = runningDays;
    }

    public double getTotalInstallCap() {
        return totalInstallCap;
    }

    public void setTotalInstallCap(double totalInstallCap) {
        this.totalInstallCap = totalInstallCap;
    }

    public int getAlreayMerge() {
        return alreayMerge;
    }

    public void setAlreayMerge(int alreayMerge) {
        this.alreayMerge = alreayMerge;
    }

    public int getUnderConstruction() {
        return underConstruction;
    }

    public void setUnderConstruction(int underConstruction) {
        this.underConstruction = underConstruction;
    }

    public int getMergeBuy() {
        return mergeBuy;
    }

    public void setMergeBuy(int mergeBuy) {
        this.mergeBuy = mergeBuy;
    }
}
