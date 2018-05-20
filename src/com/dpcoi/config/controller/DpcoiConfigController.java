package com.dpcoi.config.controller;/**
 * Created by liangzhifu
 * DATE:2017/6/17.
 */

import com.dpcoi.config.domain.DpcoiConfig;
import com.dpcoi.config.query.DpcoiConfigQuery;
import com.dpcoi.config.service.DpcoiConfigService;
import com.success.web.framework.util.AjaxUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author lzf
 * @create 2017-06-17
 **/
@Controller
@RequestMapping("/dpcoiConfig")
public class DpcoiConfigController {

    @Resource(name = "dpcoiConfigService")
    private DpcoiConfigService dpcoiConfigService;

    @RequestMapping("/getDpcoiConfigListDlg.do")
    public String getDpcoiConfigListDlg() throws Exception{
        return "dpcoi/dpcoiConfigList";
    }

    /**
     * 查询下拉菜单选项列表--分页
     * @param response 参数
     * @param dpcoiConfigQuery 查询条件
     */
    @RequestMapping("/getDpcoiConfigListPage.do")
    public void getDpcoiConfigListPage(HttpServletResponse response, DpcoiConfigQuery dpcoiConfigQuery){
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            List<Map<String, Object>> dpcoiConfigList = this.dpcoiConfigService.queryDpcoiConfigPageList(dpcoiConfigQuery);
            Integer dpcoiConfigCount = this.dpcoiConfigService.queryDpcoiConfigCount(dpcoiConfigQuery);
            Integer pageCount = dpcoiConfigCount / dpcoiConfigQuery.getSize() + (dpcoiConfigCount % dpcoiConfigQuery.getSize() > 0 ? 1 : 0);
            map.put("dpcoiConfigList", dpcoiConfigList);
            map.put("dpcoiConfigCount", dpcoiConfigCount);
            map.put("pageCount", pageCount);
            map.put("success", true);
        }catch (Exception e){
            e.printStackTrace();
            map.put("success", false);
            map.put("message", e.getMessage());
        }
        AjaxUtil.ajaxResponse(response, new JSONObject(map).toString(), AjaxUtil.RESPONCE_TYPE_JSON);
    }

    /**
     * 查询下拉菜单选项列表
     * @param response 参数
     * @param dpcoiConfigQuery 查询条件
     */
    @RequestMapping("/getDpcoiConfigList.do")
    public void getDpcoiConfigList(HttpServletResponse response, DpcoiConfigQuery dpcoiConfigQuery){
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            List<Map<String, Object>> dpcoiConfigList = this.dpcoiConfigService.queryDpcoiConfigList(dpcoiConfigQuery);
            map.put("dpcoiConfigList", dpcoiConfigList);
            map.put("success", true);
        }catch (Exception e){
            e.printStackTrace();
            map.put("success", false);
            map.put("message", e.getMessage());
        }
        AjaxUtil.ajaxResponse(response, new JSONObject(map).toString(), AjaxUtil.RESPONCE_TYPE_JSON);
    }

    /**
     * 新增下拉菜单选项
     * @param response 参数
     * @param dpcoiConfig 下拉菜单选项
     */
    @RequestMapping("/addDpcoiConfig.do")
    public void addDpcoiConfig(HttpServletResponse response, DpcoiConfig dpcoiConfig){
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            dpcoiConfig.setDeleteState("0");
            this.dpcoiConfigService.addDpcoiConfig(dpcoiConfig);
            map.put("success", true);
        }catch (Exception e){
            e.printStackTrace();
            map.put("success", false);
            map.put("message", e.getMessage());
        }
        AjaxUtil.ajaxResponse(response, new JSONObject(map).toString(), AjaxUtil.RESPONCE_TYPE_JSON);
    }

    /**
     * 删除下拉菜单选项
     * @param response 参数
     * @param dpcoiConfig 下拉菜单选项
     */
    @RequestMapping("deleteDpcoiConfig.do")
    public void deleteDpcoiConfig(HttpServletResponse response, DpcoiConfig dpcoiConfig){
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            this.dpcoiConfigService.deleteDpcoiConfig(dpcoiConfig);
            map.put("success", true);
        }catch (Exception e){
            e.printStackTrace();
            map.put("success", false);
            map.put("message", e.getMessage());
        }
        AjaxUtil.ajaxResponse(response, new JSONObject(map).toString(), AjaxUtil.RESPONCE_TYPE_JSON);
    }

    /**
     * 上传Excel
     * @param file Excel文件
     * @return 返回结果
     * @throws Exception
     */
    @RequestMapping(value = "/updateFileExcel.do", method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Object updateFileExcel(@RequestParam("excelFile") MultipartFile file) throws Exception{
        Map<String,Object> result=new HashMap<String,Object>();
        try {
            if (!file.isEmpty()) {
                if ("application/vnd.ms-excel".equals(file.getContentType())) {
                    HSSFWorkbook wb = new HSSFWorkbook(file.getInputStream());
                    this.dpcoiConfigService.addUploadFile(wb);
                }
            }
            result.put("success", "上传成功");
        }catch (Exception e){
            result.put("error", e.getMessage());
        }
        return result;
    }

    /**
     * 下载Excel
     * @param httpServletRequest 参数
     * @param dpcoiConfigQuery 查询条件
     * @return 返回结果
     */
    @RequestMapping(value = "/downExcel.do")
    @ResponseBody
    public Object downExcel(HttpServletRequest httpServletRequest, DpcoiConfigQuery dpcoiConfigQuery){
        Map<String,Object> map=new HashMap<String,Object>();
        try{
            String path = httpServletRequest.getSession().getServletContext().getRealPath("/");
            String fileName = this.dpcoiConfigService.doExportExcle(dpcoiConfigQuery, path);
            map.put("success", true);
            map.put("path", "/stdout/" + fileName);
        }catch (Exception e){
            map.put("success", false);
        }
        return map;
    }
}
