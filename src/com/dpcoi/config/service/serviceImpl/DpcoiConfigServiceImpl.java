package com.dpcoi.config.service.serviceImpl;/**
 * Created by liangzhifu
 * DATE:2017/6/17.
 */

import com.dpcoi.config.dao.DpcoiConfigCodeDao;
import com.dpcoi.config.dao.DpcoiConfigDao;
import com.dpcoi.config.domain.DpcoiConfig;
import com.dpcoi.config.domain.DpcoiConfigCode;
import com.dpcoi.config.query.DpcoiConfigQuery;
import com.dpcoi.config.service.DpcoiConfigService;
import com.success.web.framework.exception.ServiceException;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Font;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 *
 * @author lzf
 * @create 2017-06-17
 **/
@Service("dpcoiConfigService")
public class DpcoiConfigServiceImpl implements DpcoiConfigService {

    @Resource(name="dpcoiConfigDao")
    private DpcoiConfigDao dpcoiConfigDao;

    @Resource(name = "dpcoiConfigCodeDao")
    private DpcoiConfigCodeDao dpcoiConfigCodeDao;

    @Override
    public Integer addDpcoiConfig(DpcoiConfig dpcoiConfig) throws ServiceException {
        return this.dpcoiConfigDao.insertDpcoiConfig(dpcoiConfig);
    }

    @Override
    public Integer deleteDpcoiConfig(DpcoiConfig dpcoiConfig) throws ServiceException {
        dpcoiConfig.setDeleteState("1");
        return this.dpcoiConfigDao.updateDpcoiConfig(dpcoiConfig);
    }

    @Override
    public List<Map<String, Object>> queryDpcoiConfigList(DpcoiConfigQuery dpcoiConfigQuery) throws ServiceException {
        return this.dpcoiConfigDao.selectDpcoiConfigList(dpcoiConfigQuery);
    }

    @Override
    public List<Map<String, Object>> queryDpcoiConfigPageList(DpcoiConfigQuery dpcoiConfigQuery) throws ServiceException {
        return this.dpcoiConfigDao.selectDpcoiConfigListPage(dpcoiConfigQuery);
    }

    @Override
    public Integer queryDpcoiConfigCount(DpcoiConfigQuery dpcoiConfigQuery) throws ServiceException {
        return this.dpcoiConfigDao.selectDpcoiConfigCount(dpcoiConfigQuery);
    }

    @Override
    public void addUploadFile(HSSFWorkbook wb) throws ServiceException {
        if(wb != null){
            //得到第一个工作薄
            HSSFSheet sheet = wb.getSheetAt(0);
            if(sheet.getLastRowNum() <= 0){
                throw new ServiceException("excel为空文件无法导入!");
            }

            //处理文件头
            HSSFRow firstRow=sheet.getRow(0);
            for(int index = 0; index < firstRow.getLastCellNum(); index++){
                HSSFCell cell= firstRow.getCell(index);
                if(cell == null||"".equals(cell.getStringCellValue())){
                    throw new ServiceException("上传文件第["+(index+1)+"]列标题为空，请重新下载报表模板，在进行上传!");
                }
            }

            List<DpcoiConfigCode> dpcoiConfigCodeList = this.dpcoiConfigCodeDao.selectDpcoiConfigCodeList();
            List<DpcoiConfig> dpcoiConfigList = new LinkedList<DpcoiConfig>();
            //处理数据
            for(int index = 1; index < sheet.getLastRowNum()+1; index++){
                HSSFRow row = sheet.getRow(index);
                HSSFCell cell0 = row.getCell(0);
                if(cell0 == null){
                    throw new ServiceException("上传文件第1列[下拉菜单类型]为必填字段，请检查第"+(index+1)+"行是否有空值！");
                }
                String configCodeName = cell0.getStringCellValue();
                if(configCodeName == null || "".equals(configCodeName)){
                    throw new ServiceException("上传文件第1列[下拉菜单类型]为必填字段，请检查第"+(index+1)+"行是否有空值！");
                }
                HSSFCell cell1 = row.getCell(1);
                if(cell1 == null){
                    throw new ServiceException("上传文件第2列[下拉菜单选项]为必填字段，请检查第"+(index+1)+"行是否有空值！");
                }
                String configValue = cell1.getStringCellValue();
                if(configValue == null || "".equals(configValue)){
                    throw new ServiceException("上传文件第2列[下拉菜单选项]为必填字段，请检查第"+(index+1)+"行是否有空值！");
                }
                DpcoiConfig dpcoiConfig = new DpcoiConfig();
                boolean flag = false;
                for(DpcoiConfigCode dpcoiConfigCode : dpcoiConfigCodeList){
                    if(dpcoiConfigCode.getConfigCodeName().equals(configCodeName)){
                        flag = true;
                        dpcoiConfig.setConfigCodeId(dpcoiConfigCode.getConfigCodeId());
                    }
                }
                if(flag){
                    dpcoiConfig.setConfigValue(configValue);
                    dpcoiConfig.setDeleteState("0");
                    dpcoiConfigList.add(dpcoiConfig);
                }else {
                    throw new ServiceException("上传文件第1列[下拉菜单类型]不存在，请检查第"+(index+1)+"行值！");
                }
            }

            //插入数据库
            for(DpcoiConfig dpcoiConfig : dpcoiConfigList){
                this.dpcoiConfigDao.insertDpcoiConfig(dpcoiConfig);
            }
        }
    }

    @Override
    public String doExportExcle(DpcoiConfigQuery dpcoiConfigQuery, String path) throws Exception {
        String fileName = "";
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        fileName = sdf.format(date)+"_"+uuid+".xls";

        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("下拉菜单列表");

        //样式1
        HSSFCellStyle style = wb.createCellStyle();
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        //设置标题字体格式
        Font font = wb.createFont();
        //设置字体样式
        font.setFontHeightInPoints((short)20);
        font.setFontName("Courier New");

        String[] headers = {"下拉菜单类型", "下拉菜单选项"};
        HSSFRow row = sheet.createRow(0);
        for (int i = 0; i < headers.length; i++) {
            HSSFCell cell = row.createCell(i);
            cell.setCellStyle(style);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }

        List<Map<String, Object>> mapList = this.queryDpcoiConfigList(dpcoiConfigQuery);
        for (int i = 0; i < mapList.size(); i++) {
            Map<String, Object> map = mapList.get(i);
            row = sheet.createRow(i + 1);

            //下拉菜单类型
            HSSFCell cell = row.createCell(0);
            cell.setCellStyle(style);
            HSSFRichTextString text = new HSSFRichTextString((String)map.get("configCodeName"));
            cell.setCellValue(text);

            //下拉菜单选项
            cell = row.createCell(1);
            cell.setCellStyle(style);
            text = new HSSFRichTextString((String)map.get("configValue"));
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
}
