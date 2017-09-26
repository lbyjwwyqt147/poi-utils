package com.poi.poiuitls.dto;

import java.io.Serializable;

public class ReportDto implements Serializable {

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
}
