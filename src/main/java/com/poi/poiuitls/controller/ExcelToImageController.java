package com.poi.poiuitls.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.poi.poiuitls.dto.ReportBasicsDto;
import com.poi.poiuitls.dto.ReportDetailsDto;
import com.poi.poiuitls.excel.ExcelExportUtil;
import org.springframework.beans.factory.annotation.Value;

import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.ConcurrentMap;
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

            /** 报表基础信息 */
            ReportBasicsDto reportDto = new ReportBasicsDto();
            reportDto.setInning(200l);                            //局数
            reportDto.setBillingDate("2017-09-26");              //账单日期
            reportDto.setFirstPackageDate("2017-09-26");         //首包日期
            reportDto.setLastPackageDate("2017-10-10");           //尾包日期
            reportDto.setNumberOfBets(100l);                      //下注人数
            reportDto.setBestMantissa(20l);                       //最佳尾数
            reportDto.setBankerNickname("张三");                  //庄家昵称
            reportDto.setTotalHandle("10000.00");                 //投注总额
            reportDto.setMantissa("40");                          //尾数
            reportDto.setPoints("50");                            //点数
            reportDto.setProfitAndLoss("70");                     //盈亏
            reportDto.setBureauOfIntegral("1083");                 //上局积分
            reportDto.setRemainingPoints("300");                  //剩余积分


            /** 报表详细数据   */
            CopyOnWriteArrayList<ConcurrentMap<String,Object>> reportDetailsList = new CopyOnWriteArrayList<>();
            for (int i = 0;i<10;i++){
                ReportDetailsDto reportDetailsDto = new ReportDetailsDto();
                reportDetailsDto.setOrderNumber(i+1L);

                reportDetailsDto.setBottomPour(""+i);
                reportDetailsDto.setBunko(""+i);
                reportDetailsDto.setDotPerInch(""+i);
                reportDetailsDto.setLastIntegral(""+i);
                reportDetailsDto.setMstopssa(""+i);
                reportDetailsDto.setPlayerNickname("玩家"+i);
                reportDetailsDto.setResiduentegral(""+i);

                ConcurrentMap<String,Object> map = JSONObject.parseObject(JSON.toJSONString(reportDetailsDto), new TypeReference<ConcurrentMap<String,Object>>(){});

                reportDetailsList.add(map);
            }



            //总金额
           /* String totalMoney = "0.00";
            double subtotalMoeny = 0.00;
            DecimalFormat myformat = new DecimalFormat();
            myformat.applyPattern("##,###.00");
            totalMoney = myformat.format(subtotalMoeny);*/

           // HttpServletResponse response =
            ExcelExportUtil.produceExcelDatas( null, xlsPath, reportDetailsList, 6,"赌局","2017", JSON.toJSONString(reportDto),1,8,false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
