package com.poi.poiuitls.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Excel导出
 * @author liujunyi
 *
 */
public class ExcelExportUtil {
	
	/**
	 * 创建Excel
	 * @param sheet
	 * @param startRow  开始行
	 * @param dataList  数据集合
	 * @param startCell 从第几列开始复制
	 * @param endCell   总共列数
	 * @param regions
	 */
	public static void initXls(Sheet sheet, int startRow, CopyOnWriteArrayList<ConcurrentMap<String,Object>> dataList, List<CellRangeAddress> regions, int startCell, int endCell, boolean flag){
		try {
			int shiftStart = startRow+1;
			Row mbRow = sheet.getRow(startRow);
			System.out.println(sheet.getLastRowNum());
			sheet.shiftRows(startRow + 1, sheet.getLastRowNum(), dataList.size(), true, false);
			startRow = startRow - 1;
			for (Map<String,Object> map:dataList) {
				Row sourceRow = null;
				Row targetRow = null;
				Cell sourceCell = null;
				Cell targetCell = null;
				int m;
				startRow = startRow + 1;
				sourceRow = sheet.getRow(startRow);
				if (sourceRow == null) {
					sourceRow = sheet.createRow(startRow);
				}
				targetRow = sheet.createRow(startRow + 1);
				targetRow.setHeight(sourceRow.getHeight());
				
				for (m = sourceRow.getFirstCellNum(); m < sourceRow.getLastCellNum(); m++) {
					sourceCell = mbRow.getCell(m);
					targetCell = targetRow.createCell(m);
					targetCell.setCellStyle(sourceCell.getCellStyle());
					targetCell.setCellType(sourceCell.getCellType());
					sourceCell.setCellType(Cell.CELL_TYPE_STRING);
				
					if(flag){
						String cellName = sourceCell.getStringCellValue();
						targetCell.setCellValue(String.valueOf(map.get(cellName)));
					}else{
						if(endCell != 0 && m != endCell){
							if(startCell != 0 && m != startCell){
								String cellName = sourceCell.getStringCellValue();
								targetCell.setCellValue(String.valueOf(map.get(cellName)));
								
							}
						}
						
					}
					
					
				}
				if(regions!=null)
					for(CellRangeAddress regoin:regions)
						sheet.addMergedRegion(regoin);
			}
			
			sheet.shiftRows(shiftStart,sheet.getLastRowNum(),-1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	/**
	 * 将数据填充到excel 模板中
	 * @param response
	 * @param xlsPath
	 * @param dataList
	 * @param startRow
	 * @param xlsName
	 * @param descripe
	 * @param otherJsonParams
	 * @param startCell
	 * @param endCell
	 * @param flag
	 * @param regions
	 */
	public static void produceExcelDatas(HttpServletResponse response, String xlsPath,
										 CopyOnWriteArrayList<ConcurrentMap<String,Object>> dataList, int startRow, String xlsName, String descripe, String otherJsonParams, int startCell, int endCell, boolean flag, List<CellRangeAddress> ... regions){
		try {
			InputStream in = new FileInputStream(new File(xlsPath));
			Workbook work = null;
			Sheet sheet = null;
			try {
				work = new HSSFWorkbook(in);
			} catch (Exception e) {
				in = new FileInputStream(new File(xlsPath));
				work = new XSSFWorkbook(in);
			}
			sheet = work.getSheetAt(0);
			outer:for(int i=sheet.getFirstRowNum();i<sheet.getLastRowNum();i++){
				Row row = sheet.getRow(i);
				Set<String> set = null;
				JSONObject jsonObj = JSONObject.parseObject("{}");
				if(otherJsonParams != null){
					jsonObj =JSONObject.parseObject(otherJsonParams);
					set = jsonObj.keySet();
				}

				if(row != null){
					for (int m = row.getFirstCellNum(); m < row.getLastCellNum(); m++) {
						String cellName = row.getCell(m).getStringCellValue();

						if(otherJsonParams != null){
							for(String key : set){

								if(key.equals(cellName)){
									row.getCell(m).setCellValue(jsonObj.getString(key));
									//break outer;
								}
							}
						}
						/*if("descripe".equals(cellName)){
							row.getCell(m).setCellValue(descripe);
							break outer;
						}*/
					}
				}

			}
//			HSSFSheet sheet = work.getSheetAt(0);
			ExcelExportUtil.initXls(sheet, startRow, dataList, regions.length>0?regions[0]:null,startCell,endCell,flag);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	/**
	 * 
	* @Title: exceptXLS 
	* @Description: TODO 导出xls文件
	* @param response 请求响应对象
	* @param xlsPath 模板路径
	* @param dataList 导出数据
	* @param startRow 数据开始行
	* @param regions 表格合并对象
	* @return void 返回类型
	 */
	public static void exportXLS(HttpServletResponse response,String xlsPath,
								 CopyOnWriteArrayList<ConcurrentMap<String,Object>> dataList,int startRow,String xlsName,int startCell,int endCell,boolean flag,List<CellRangeAddress> ... regions){
		try {
			InputStream in = new FileInputStream(new File(xlsPath));
			Workbook work = null;
			Sheet sheet = null;
			try {
				work = new HSSFWorkbook(in);
			} catch (Exception e) {
				in = new FileInputStream(new File(xlsPath));
				work = new XSSFWorkbook(in);
			}
			sheet = work.getSheetAt(0);
//			HSSFSheet sheet = work.getSheetAt(0);
			ExcelExportUtil.initXls(sheet, startRow, dataList, regions.length>0?regions[0]:null,startCell,endCell,flag);
			
			response.setContentType("octets/stream"); 
			response.addHeader("Content-Disposition", "attachment;filename="+new String(xlsName.getBytes("utf-8"), "ISO8859-1" )+".xls");
			work.write(response.getOutputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 导出Excel
	 * @param response
	 * @param xlsPath  模板文件路径
	 * @param dataList list集合数据
	 * @param startRow 从第几行开始
	 * @param xlsName  下载后的文件名称
	 * @param descripe
	 * @param regions  表格合并对象
	 */
	@SuppressWarnings("unchecked")
	public static void exportXLS(HttpServletResponse response,String xlsPath,
								 CopyOnWriteArrayList<ConcurrentMap<String,Object>> dataList,int startRow,String xlsName,String descripe,String otherJsonParams,int startCell,int endCell,boolean flag,List<CellRangeAddress> ... regions){
		try {
			InputStream in = new FileInputStream(new File(xlsPath));
			Workbook work = null;
			Sheet sheet = null;
			try {
				work = new HSSFWorkbook(in);
			} catch (Exception e) {
				in = new FileInputStream(new File(xlsPath));
				work = new XSSFWorkbook(in);
			}
			sheet = work.getSheetAt(0);
			outer:for(int i=sheet.getFirstRowNum();i<sheet.getLastRowNum();i++){
				Row row = sheet.getRow(i);
				 Set<String> set = null;
				 JSONObject jsonObj = JSONObject.parseObject("{}");
				if(otherJsonParams != null){
					jsonObj =JSONObject.parseObject(otherJsonParams);
					set = jsonObj.keySet();
				}
				 
				if(row != null){
					for (int m = row.getFirstCellNum(); m < row.getLastCellNum(); m++) {
						String cellName = row.getCell(m).getStringCellValue();
				
						if(otherJsonParams != null){
						 for(String key : set){
						   
						    if(key.equals(cellName)){
								row.getCell(m).setCellValue(jsonObj.getString(key));
								//break outer;
							}
						 }
						}
						/*if("descripe".equals(cellName)){
							row.getCell(m).setCellValue(descripe);
							break outer;
						}*/
					}
				}
				
			}
//			HSSFSheet sheet = work.getSheetAt(0);
			ExcelExportUtil.initXls(sheet, startRow, dataList, regions.length>0?regions[0]:null,startCell,endCell,flag);
			
			response.setContentType("octets/stream"); 
			response.addHeader("Content-Disposition", "attachment;filename="+new String(xlsName.getBytes("utf-8"), "ISO8859-1" )+".xls");
			work.write(response.getOutputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
