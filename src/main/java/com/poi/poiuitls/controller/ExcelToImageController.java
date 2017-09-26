package com.poi.poiuitls.controller;

import org.springframework.beans.factory.annotation.Value;

import java.util.concurrent.CopyOnWriteArrayList;

public class ExcelToImageController {

    @Value("${com.poi.poiutils.templates.report}")
    String templateXlsPath;

    /**
     * 导出
     */
    public void exportExcel(){
        //存放List数据
        CopyOnWriteArrayList<Object> resultList = new CopyOnWriteArrayList<>();
        try {

            //得到模板配置路径
            String xlsPath = templateXlsPath;
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



            String[] strings = null;
            //获取食品台帐出库的食品出库明细信息
            List<?> list = exwarehouseRelateMgr.findAllList(additiveId, (String) getRequest().getSession().getAttribute(
                    "SESSION_CORE_ORG"), strings);
            int i = 1;
            //总金额
            String totalMoney = "0.00";
            double subtotalMoeny = 0.00;
            DecimalFormat myformat = new DecimalFormat();
            myformat.applyPattern("##,###.00");
            if(list  != null && list.size() > 0){
                Iterator<?> it = list.iterator();
                while(it.hasNext()){
                    Map<String, Object> map = new HashMap<String, Object>();
                    TDailyFoodExwarehouseRelate _E = (TDailyFoodExwarehouseRelate) it.next();
                    TPsiFoodInfo info = foodInfoMgr.get( _E.getFoodId());
                    if(info != null ){
                        map.put("foodName", info.getFoodName());
                        map.put("foodSpec", info.getFoodSpec());
                        map.put("foodUnit",dictMgr.getDictNameByHcode(info.getFoodUnit(),
                                "TPsiFoodInfo", "foodUnit"));
                        map.put("foodPrice", info.getFoodPrice() != null ? info.getFoodPrice() : "");
                    }else{
                        map.put("foodName", "");
                        map.put("foodSpec", "");
                        map.put("foodUnit", "");
                        map.put("foodPrice", "");
                    }
                    map.put("thisUse", _E.getThisUse());
                    map.put("money", _E.getMoney() != null  && _E.getMoney().equals("--") ? "": _E.getMoney() );
                    map.put("useage", _E.getUsefor());
                    String formatString = "";
                    if ( _E.getMoney()!= null &&  _E.getMoney().length() >= 1) {
                        formatString =  _E.getMoney().replaceAll(",", "");
                    }
                    if(!formatString.equals("--")){
                        subtotalMoeny = subtotalMoeny+Double.valueOf(formatString);

                    }

                    map.put("favorableMoney", "0");
                    map.put("rowNum", i);
                    i++;
                    resultList.add(map);
                }
            }
            if(subtotalMoeny != 0.0){
                totalMoney = myformat.format(subtotalMoeny);
            }else{
                totalMoney = "";
            }


            String success = "{\"buyDate\":\""+buyDate+"\",\"batchNum\":\""+batchNum+"\",\"infoMate\":\""+infoMate+"\",\"description\":\""+description+"\",\"remark\":\""+remark+"\",\"totalMoney\":\""+totalMoney+"\"}";

            ExcelExportUtil.exportXLS(getResponse(), xlsPath, resultList, 6,buyDate+"出库单",buyDate,success,1,11,false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
