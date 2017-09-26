package com.poi.poiuitls.dto;

import java.io.Serializable;

public class ReportBasicsDto implements Serializable {

    private Long inning;              //局数
    private String billingDate;       //账单日期
    private String firstPackageDate;  //首包日期
    private String lastPackageDate;   //尾包日期
    private Long   numberOfBets;       //下注人数
    private Long   bestMantissa;       //最佳尾数
    private String bankerNickname;     //庄家昵称
    private String totalHandle;        //投注总额
    private String mantissa;           //尾数
    private String points;             //点数
    private String profitAndLoss;      //盈亏
    private String bureauOfIntegral;   //上局积分
    private String remainingPoints;    //剩余积分


    public Long getInning() {
        return inning;
    }

    public void setInning(Long inning) {
        this.inning = inning;
    }

    public String getBillingDate() {
        return billingDate;
    }

    public void setBillingDate(String billingDate) {
        this.billingDate = billingDate;
    }

    public String getFirstPackageDate() {
        return firstPackageDate;
    }

    public void setFirstPackageDate(String firstPackageDate) {
        this.firstPackageDate = firstPackageDate;
    }

    public String getLastPackageDate() {
        return lastPackageDate;
    }

    public void setLastPackageDate(String lastPackageDate) {
        this.lastPackageDate = lastPackageDate;
    }

    public Long getNumberOfBets() {
        return numberOfBets;
    }

    public void setNumberOfBets(Long numberOfBets) {
        this.numberOfBets = numberOfBets;
    }

    public Long getBestMantissa() {
        return bestMantissa;
    }

    public void setBestMantissa(Long bestMantissa) {
        this.bestMantissa = bestMantissa;
    }

    public String getBankerNickname() {
        return bankerNickname;
    }

    public void setBankerNickname(String bankerNickname) {
        this.bankerNickname = bankerNickname;
    }

    public String getTotalHandle() {
        return totalHandle;
    }

    public void setTotalHandle(String totalHandle) {
        this.totalHandle = totalHandle;
    }

    public String getMantissa() {
        return mantissa;
    }

    public void setMantissa(String mantissa) {
        this.mantissa = mantissa;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    public String getProfitAndLoss() {
        return profitAndLoss;
    }

    public void setProfitAndLoss(String profitAndLoss) {
        this.profitAndLoss = profitAndLoss;
    }

    public String getBureauOfIntegral() {
        return bureauOfIntegral;
    }

    public void setBureauOfIntegral(String bureauOfIntegral) {
        this.bureauOfIntegral = bureauOfIntegral;
    }

    public String getRemainingPoints() {
        return remainingPoints;
    }

    public void setRemainingPoints(String remainingPoints) {
        this.remainingPoints = remainingPoints;
    }
}
