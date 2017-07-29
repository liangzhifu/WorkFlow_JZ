package com.dpcoi.drived.service.serviceImpl;/**
 * Created by liangzhifu
 * DATE:2017/7/3.
 */

import com.dpcoi.drived.service.ExportExcelService;
import com.dpcoi.rr.dao.RRProblemDao;
import com.dpcoi.rr.query.RRProblemQuery;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Font;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 *
 * @author lzf
 * @create 2017-07-03
 **/
@Service("exportExcelService")
public class ExportExcelServiceImpl implements ExportExcelService {

    @Resource(name = "rRProblemDao")
    private RRProblemDao rRProblemDao;

    @Override
    public String excelRRProblem(String path, RRProblemQuery rrProblemQuery) throws Exception {
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

        String[] headers = {"状态", "问题编号", "问题类型", "工程", "问题进展",
                "追踪等级", "发生日期", "客户", "车型", "品名",
                "不良内容", "责任人", "下次汇报时间", "进度", "延期原因及延期进展",
                "第一次原因调查", "第二次永久对策", "第三次永久对策有效", "第四次经验总结", "关闭确认",
                "生产线", "严重度", "发生频次", "不良数量", "批次",
                "发生班次", "责任部门", "客户处是否记录PPM", "记录数量", "临时对策（4H）",
                "根本原因（48H）", "永久对策（14D）", "效果验证（34D）", "品情联编号", "质量警示卡编号",
                "品推表", "PFMEA", "C.P.QC工程表", "作业标准书", "设备点检表",
                "始终件表", "检查基准书", "检查手顺书", "教育议事录", "变化点管理",
                "展开及追踪是否完成", "人工", "物料"};
        HSSFRow row = sheet.createRow(0);   //--->创建一行
        for (short i = 0; i < headers.length; i++) {
            HSSFCell cell = row.createCell(i);
            cell.setCellStyle(style);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }

        List<Map<String, Object>> rrProblemList = this.rRProblemDao.selectRRProblemList(rrProblemQuery);
        for (int i = 0; i < rrProblemList.size(); i++){
            row = sheet.createRow(i + 1);
            Map map = rrProblemList.get(i);

            //状态
            HSSFCell cell = row.createCell(0);
            cell.setCellStyle(style);
            HSSFRichTextString text = new HSSFRichTextString((String)map.get("problemStatus"));
            cell.setCellValue(text);

            //问题编号
            cell = row.createCell(1);
            cell.setCellStyle(style);
            text = new HSSFRichTextString((String)map.get("problemNo"));
            cell.setCellValue(text);

            //问题类型
            cell = row.createCell(2);
            cell.setCellStyle(style);
            text = new HSSFRichTextString((String)map.get("problemType"));
            cell.setCellValue(text);

            //工程
            cell = row.createCell(3);
            cell.setCellStyle(style);
            text = new HSSFRichTextString((String)map.get("engineering"));
            cell.setCellValue(text);

            //问题进展
            cell = row.createCell(4);
            cell.setCellStyle(style);
            text = new HSSFRichTextString((String)map.get("problemProgress"));
            cell.setCellValue(text);

            //追踪等级
            cell = row.createCell(5);
            cell.setCellStyle(style);
            text = new HSSFRichTextString((String)map.get("trackingLevel"));
            cell.setCellValue(text);

            //发生日期
            Date happenDate = (Date)map.get("happenDate");
            String happenDateStr;
            if(happenDate == null){
                happenDateStr = "";
            }else {
                happenDateStr = sdf.format(happenDate);
            }
            cell = row.createCell(6);
            cell.setCellStyle(style);
            text = new HSSFRichTextString(happenDateStr);
            cell.setCellValue(text);

            //客户
            cell = row.createCell(7);
            cell.setCellStyle(style);
            text = new HSSFRichTextString((String)map.get("customer"));
            cell.setCellValue(text);

            //车型
            cell = row.createCell(8);
            cell.setCellStyle(style);
            text = new HSSFRichTextString((String)map.get("vehicle"));
            cell.setCellValue(text);

            //品名
            cell = row.createCell(9);
            cell.setCellStyle(style);
            text = new HSSFRichTextString((String)map.get("productNo"));
            cell.setCellValue(text);

            //不良内容
            cell = row.createCell(10);
            cell.setCellStyle(style);
            text = new HSSFRichTextString((String)map.get("badContent"));
            cell.setCellValue(text);

            //责任人
            cell = row.createCell(11);
            cell.setCellStyle(style);
            text = new HSSFRichTextString((String)map.get("persionLiable"));
            cell.setCellValue(text);

            //下次汇报时间
            Date reportDate = (Date)map.get("reportDate");
            String reportDateStr;
            if(reportDate == null){
                reportDateStr = "";
            }else {
                reportDateStr = sdf.format(reportDate);
            }
            cell = row.createCell(12);
            cell.setCellStyle(style);
            text = new HSSFRichTextString(reportDateStr);
            cell.setCellValue(text);

            //进度
            cell = row.createCell(13);
            cell.setCellStyle(style);
            text = new HSSFRichTextString((String)map.get("speedOfProgress"));
            cell.setCellValue(text);

            //延期原因及延期进展
            cell = row.createCell(14);
            cell.setCellStyle(style);
            text = new HSSFRichTextString((String)map.get("reasonForDelay"));
            cell.setCellValue(text);

            //第一次原因调查
            Date firstDate = (Date)map.get("firstDate");
            String firstDateStr;
            if(firstDate == null){
                firstDateStr = "";
            }else {
                firstDateStr = sdf.format(firstDate);
            }
            cell = row.createCell(15);
            cell.setCellStyle(style);
            text = new HSSFRichTextString(firstDateStr);
            cell.setCellValue(text);

            //第二次永久对策
            Date secondDate = (Date)map.get("secondDate");
            String secondDateStr;
            if(secondDate == null){
                secondDateStr = "";
            }else {
                secondDateStr = sdf.format(secondDate);
            }
            cell = row.createCell(16);
            cell.setCellStyle(style);
            text = new HSSFRichTextString(secondDateStr);
            cell.setCellValue(text);

            //第三次永久对策有效
            Date thirdDate = (Date)map.get("thirdDate");
            String thirdDateStr;
            if(thirdDate == null){
                thirdDateStr = "";
            }else {
                thirdDateStr = sdf.format(thirdDate);
            }
            cell = row.createCell(17);
            cell.setCellStyle(style);
            text = new HSSFRichTextString(thirdDateStr);
            cell.setCellValue(text);

            //第四次经验总结
            Date fourthDate = (Date)map.get("fourthDate");
            String fourthDateStr;
            if(fourthDate == null){
                fourthDateStr = "";
            }else {
                fourthDateStr = sdf.format(fourthDate);
            }
            cell = row.createCell(18);
            cell.setCellStyle(style);
            text = new HSSFRichTextString(fourthDateStr);
            cell.setCellValue(text);

            //关闭确认
            cell = row.createCell(19);
            cell.setCellStyle(style);
            text = new HSSFRichTextString((String)map.get("closeConfirm"));
            cell.setCellValue(text);

            //生产线
            cell = row.createCell(20);
            cell.setCellStyle(style);
            text = new HSSFRichTextString((String)map.get("productLine"));
            cell.setCellValue(text);

            //严重度
            cell = row.createCell(21);
            cell.setCellStyle(style);
            text = new HSSFRichTextString((String)map.get("severity"));
            cell.setCellValue(text);

            //发生频次
            cell = row.createCell(22);
            cell.setCellStyle(style);
            text = new HSSFRichTextString((String)map.get("occurrenceFrequency"));
            cell.setCellValue(text);

            //不良数量
            cell = row.createCell(23);
            cell.setCellStyle(style);
            text = new HSSFRichTextString((String)map.get("badQuantity"));
            cell.setCellValue(text);

            //批次
            cell = row.createCell(24);
            cell.setCellStyle(style);
            text = new HSSFRichTextString((String)map.get("batch"));
            cell.setCellValue(text);

            //发生班次
            cell = row.createCell(25);
            cell.setCellStyle(style);
            text = new HSSFRichTextString((String)map.get("happenShift"));
            cell.setCellValue(text);

            //责任部门
            cell = row.createCell(26);
            cell.setCellStyle(style);
            text = new HSSFRichTextString((String)map.get("responsibleDepartment"));
            cell.setCellValue(text);

            //客户处是否记录PPM
            cell = row.createCell(27);
            cell.setCellStyle(style);
            text = new HSSFRichTextString((String)map.get("recordPpm"));
            cell.setCellValue(text);

            //记录数量
            cell = row.createCell(28);
            cell.setCellStyle(style);
            text = new HSSFRichTextString((String)map.get("recordNum"));
            cell.setCellValue(text);

            //临时对策（4H）
            cell = row.createCell(29);
            cell.setCellStyle(style);
            text = new HSSFRichTextString((String)map.get("temporary"));
            cell.setCellValue(text);

            //根本原因（48H）
            cell = row.createCell(30);
            cell.setCellStyle(style);
            text = new HSSFRichTextString((String)map.get("rootCause"));
            cell.setCellValue(text);

            //永久对策（14D）
            cell = row.createCell(31);
            cell.setCellStyle(style);
            text = new HSSFRichTextString((String)map.get("permanentGame"));
            cell.setCellValue(text);

            //效果验证（34D）
            cell = row.createCell(32);
            cell.setCellStyle(style);
            text = new HSSFRichTextString((String)map.get("effectVerification"));
            cell.setCellValue(text);

            //品情联编号
            cell = row.createCell(33);
            cell.setCellStyle(style);
            text = new HSSFRichTextString((String)map.get("serialNumber"));
            cell.setCellValue(text);

            //质量警示卡编号
            cell = row.createCell(34);
            cell.setCellStyle(style);
            text = new HSSFRichTextString((String)map.get("qualityWarningCardNumber"));
            cell.setCellValue(text);

            //品推表
            cell = row.createCell(35);
            cell.setCellStyle(style);
            text = new HSSFRichTextString((String)map.get("productScale"));
            cell.setCellValue(text);

            //PFMEA
            cell = row.createCell(36);
            cell.setCellStyle(style);
            text = new HSSFRichTextString((String)map.get("pfmea"));
            cell.setCellValue(text);

            //C.P.QC工程表
            cell = row.createCell(37);
            cell.setCellStyle(style);
            text = new HSSFRichTextString((String)map.get("cp"));
            cell.setCellValue(text);

            //作业标准书
            cell = row.createCell(38);
            cell.setCellStyle(style);
            text = new HSSFRichTextString((String)map.get("standardBook"));
            cell.setCellValue(text);

            //设备点检表
            cell = row.createCell(39);
            cell.setCellStyle(style);
            text = new HSSFRichTextString((String)map.get("equipmentChecklist"));
            cell.setCellValue(text);

            //始终件表
            cell = row.createCell(40);
            cell.setCellStyle(style);
            text = new HSSFRichTextString((String)map.get("alwaysList"));
            cell.setCellValue(text);

            //检查基准书
            cell = row.createCell(41);
            cell.setCellStyle(style);
            text = new HSSFRichTextString((String)map.get("inspectionReferenceBook"));
            cell.setCellValue(text);

            //检查手顺书
            cell = row.createCell(42);
            cell.setCellStyle(style);
            text = new HSSFRichTextString((String)map.get("inspectionBook"));
            cell.setCellValue(text);

            //教育议事录
            cell = row.createCell(43);
            cell.setCellStyle(style);
            text = new HSSFRichTextString((String)map.get("education"));
            cell.setCellValue(text);

            //变化点管理
            cell = row.createCell(44);
            cell.setCellStyle(style);
            text = new HSSFRichTextString((String)map.get("changePoint"));
            cell.setCellValue(text);

            //展开及追踪是否完成
            cell = row.createCell(45);
            cell.setCellStyle(style);
            text = new HSSFRichTextString((String)map.get("expandTrace"));
            cell.setCellValue(text);

            //人工
            cell = row.createCell(46);
            cell.setCellStyle(style);
            text = new HSSFRichTextString((String)map.get("artificial"));
            cell.setCellValue(text);

            //物料
            cell = row.createCell(47);
            cell.setCellStyle(style);
            text = new HSSFRichTextString((String)map.get("materiel"));
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
