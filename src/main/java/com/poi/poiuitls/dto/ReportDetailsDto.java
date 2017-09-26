package com.poi.poiuitls.dto;

import java.io.Serializable;

@Data
public class ReportDetailsDto implements Serializable {

    private Long orderNumber;              //序号
    private String playerNickname;        //玩家昵称
    private String bottomPour;           //下注
    private String mstopssa;             //尾数
    private String dotPerInch;           //点数
    private String bunko;                //本局输赢
    private String lastIntegral;         //上局积分
    private String residuentegral;      //剩余积分
}
