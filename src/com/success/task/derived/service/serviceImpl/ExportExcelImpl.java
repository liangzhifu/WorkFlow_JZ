package com.success.task.derived.service.serviceImpl;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Font;
import org.springframework.stereotype.Service;

import com.success.task.derived.service.ExportExcel;
import com.success.task.detail.domain.Detail;
import com.success.task.screen.domain.TaskScreen;

@Service("exportExcel")
public class ExportExcelImpl implements ExportExcel {

	@SuppressWarnings("deprecation")
	public String excelService(List<Detail> detailList, String path) throws Exception{
		String fileName = "";
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String randNum = this.getRandNum();
		fileName = sdf.format(date)+"_"+randNum+".xls";
		HSSFWorkbook wb = new HSSFWorkbook();  //--->创建了一个excel文件    
        HSSFSheet sheet = wb.createSheet("任务列表");   //--->创建了一个工作簿    
        
        //样式1    
        HSSFCellStyle style = wb.createCellStyle(); // 样式对象    
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直    
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 水平    
        //设置标题字体格式    
        Font font = wb.createFont();       
        //设置字体样式     
        font.setFontHeightInPoints((short)20);   //--->设置字体大小    
        font.setFontName("Courier New");   //---》设置字体，是什么类型例如：宋体    
        
        String[] headers = {"发行日", "发行编号", "变更时间", "内容", "生产线", "车种", "安装席", "定单状态", "所属部门", "创建人", "创建时间", "完成时间", "是否超时", "作废原因"};
        HSSFRow row = sheet.createRow(0);   //--->创建一行
        for (short i = 0; i < headers.length; i++) {
            HSSFCell cell = row.createCell(i);
            cell.setCellStyle(style);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }
        
        for (int i = 0; i < detailList.size(); i++){
        	row = sheet.createRow(i + 1);
        	Detail detail = detailList.get(i);
        	
        	//发行日
        	HSSFCell cell = row.createCell(0);
            cell.setCellStyle(style);
            HSSFRichTextString text = new HSSFRichTextString(detail.getIssueDate());
            cell.setCellValue(text);
            
            //发行编号
            cell = row.createCell(1);
            cell.setCellStyle(style);
            text = new HSSFRichTextString(detail.getPublishCode());
            cell.setCellValue(text);
            
            //变更时间
            cell = row.createCell(2);
            cell.setCellStyle(style);
            text = new HSSFRichTextString(detail.getChangeTime());
            cell.setCellValue(text);
            
            //内容
            cell = row.createCell(3);
            cell.setCellStyle(style);
            text = new HSSFRichTextString(detail.getChangeContent());
            cell.setCellValue(text);
            
            //生产线
            cell = row.createCell(4);
            cell.setCellStyle(style);
            text = new HSSFRichTextString(detail.getProductionLine());
            cell.setCellValue(text);
            
            //车种
            cell = row.createCell(5);
            cell.setCellStyle(style);
            text = new HSSFRichTextString(detail.getCarType());
            cell.setCellValue(text);
            
            //安装席
            cell = row.createCell(6);
            cell.setCellStyle(style);
            text = new HSSFRichTextString(detail.getMountingMat());
            cell.setCellValue(text);
            
            //定单状态
            cell = row.createCell(7);
            cell.setCellStyle(style);
            if("10A".equals(detail.getOrderStateCode())){
            	text = new HSSFRichTextString("初始化");
            }else if("10B".equals(detail.getOrderStateCode())){
            	text = new HSSFRichTextString("处理中");
            }else if("10C".equals(detail.getOrderStateCode())){
            	text = new HSSFRichTextString("完成");
            }else if("10X".equals(detail.getOrderStateCode())){
            	text = new HSSFRichTextString("作废");
            }else {
            	text = new HSSFRichTextString("未知");
            }
            cell.setCellValue(text);
            
            //所属部门
            cell = row.createCell(8);
            cell.setCellStyle(style);
            text = new HSSFRichTextString(detail.getCreateOrg());
            cell.setCellValue(text);
            
            //创建人
            cell = row.createCell(9);
            cell.setCellStyle(style);
            text = new HSSFRichTextString(detail.getCreateUser());
            cell.setCellValue(text);
            
            //创建时间
            cell = row.createCell(10);
            cell.setCellStyle(style);
            text = new HSSFRichTextString(detail.getCreateTime());
            cell.setCellValue(text);
            
            //完成时间
            cell = row.createCell(11);
            cell.setCellStyle(style);
            text = new HSSFRichTextString(detail.getCompleteTime());
            cell.setCellValue(text);
            
            //是否超时
            cell = row.createCell(12);
            cell.setCellStyle(style);
            if("1".equals(detail.getIsDelay())){
            	text = new HSSFRichTextString("是");
            }else {
            	text = new HSSFRichTextString("否");
            }
            cell.setCellValue(text);
            
            //作废原因
            cell = row.createCell(13);
            cell.setCellStyle(style);
            text = new HSSFRichTextString(detail.getInvalidateText());
            cell.setCellValue(text);
            
        }
        
        FileOutputStream fileOut = null;                 
        fileOut = new FileOutputStream(path+"stdout/"+fileName);    
        wb.write(fileOut); 
        if(fileOut != null){    
            try {    
                fileOut.close();    
            } catch (IOException e) {    
                // TODO Auto-generated catch block    
                e.printStackTrace();    
            }    
        } 
        
		return fileName;
	}
	
	/**
	 * 导出屏幕展示的变更单
	 * @param detailList
	 * @param path
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("deprecation")
	public String excelScreenService(List<TaskScreen> taskScreenList, String path) throws Exception{
		String fileName = "";
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String randNum = this.getRandNum();
		fileName = sdf.format(date)+"_"+randNum+".xls";
		HSSFWorkbook wb = new HSSFWorkbook();  //--->创建了一个excel文件    
        HSSFSheet sheet = wb.createSheet("立合清单");   //--->创建了一个工作簿    
        
        //样式1    
        HSSFCellStyle style = wb.createCellStyle(); // 样式对象    
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直    
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 水平    
        //设置标题字体格式    
        Font font = wb.createFont();       
        //设置字体样式     
        font.setFontHeightInPoints((short)20);   //--->设置字体大小    
        font.setFontName("Courier New");   //---》设置字体，是什么类型例如：宋体    
        
        String[] headers = {"变更日", "发行编号", "变更内容", "生产线", "未确认部门", "确认人", "状态"};
        HSSFRow row = sheet.createRow(0);   //--->创建一行
        for (short i = 0; i < headers.length; i++) {
            HSSFCell cell = row.createCell(i);
            cell.setCellStyle(style);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }
        
        for (int i = 0; i < taskScreenList.size(); i++){
        	row = sheet.createRow(i + 1);
        	TaskScreen taskScreen = taskScreenList.get(i);
        	
        	//变更日
        	HSSFCell cell = row.createCell(0);
            cell.setCellStyle(style);
            HSSFRichTextString text = new HSSFRichTextString(taskScreen.getChangeDate().substring(5));
            cell.setCellValue(text);
            
            //发行编号
            cell = row.createCell(1);
            cell.setCellStyle(style);
            text = new HSSFRichTextString(taskScreen.getPublishCode());
            cell.setCellValue(text);
            
            //变更内容
            cell = row.createCell(2);
            cell.setCellStyle(style);
            text = new HSSFRichTextString(taskScreen.getChangeContent());
            cell.setCellValue(text);
            
            //生产线
            cell = row.createCell(3);
            cell.setCellStyle(style);
            text = new HSSFRichTextString(taskScreen.getProductionLine());
            cell.setCellValue(text);
            
            //未确认部门
            cell = row.createCell(4);
            cell.setCellStyle(style);
            text = new HSSFRichTextString(taskScreen.getTacheName());
            cell.setCellValue(text);
            
            //确认人
            cell = row.createCell(5);
            cell.setCellStyle(style);
            text = new HSSFRichTextString(taskScreen.getConfirmName());
            cell.setCellValue(text);
            
            //状态
            cell = row.createCell(6);
            cell.setCellStyle(style);
            text = new HSSFRichTextString(taskScreen.getContractResult());
            cell.setCellValue(text);
            
        }
        
        FileOutputStream fileOut = null;                 
        fileOut = new FileOutputStream(path+"stdout/"+fileName);    
        wb.write(fileOut); 
        if(fileOut != null){    
            try {    
                fileOut.close();    
            } catch (IOException e) {    
                // TODO Auto-generated catch block    
                e.printStackTrace();    
            }    
        } 
        
		return fileName;
	}
	
	private String getRandNum(){
		 Random random = new Random();
        String result = "";
        for(int i = 0; i < 6; i++){
            result+=random.nextInt(10);    
        }
        return result;
	}
	
}
