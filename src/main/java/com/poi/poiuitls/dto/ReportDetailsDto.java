package com.poi.poiuitls.dto;

import java.io.Serializable;


public class ReportDetailsDto implements Serializable {

    private Long orderNumber;              //序号
    private String playerNickname;        //玩家昵称
    private String bottomPour;           //下注
    private String mstopssa;             //尾数
    private String dotPerInch;           //点数
    private String bunko;                //本局输赢
    private String lastIntegral;         //上局积分
    private String residuentegral;      //剩余积分

    public Long getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Long orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getPlayerNickname() {
        return playerNickname;
    }

    public void setPlayerNickname(String playerNickname) {
        this.playerNickname = playerNickname;
    }

    public String getBottomPour() {
        return bottomPour;
    }

    public void setBottomPour(String bottomPour) {
        this.bottomPour = bottomPour;
    }

    public String getMstopssa() {
        return mstopssa;
    }

    public void setMstopssa(String mstopssa) {
        this.mstopssa = mstopssa;
    }

    public String getDotPerInch() {
        return dotPerInch;
    }

    public void setDotPerInch(String dotPerInch) {
        this.dotPerInch = dotPerInch;
    }

    public String getBunko() {
        return bunko;
    }

    public void setBunko(String bunko) {
        this.bunko = bunko;
    }

    public String getLastIntegral() {
        return lastIntegral;
    }

    public void setLastIntegral(String lastIntegral) {
        this.lastIntegral = lastIntegral;
    }

    public String getResiduentegral() {
        return residuentegral;
    }

    public void setResiduentegral(String residuentegral) {
        this.residuentegral = residuentegral;
    }


}
