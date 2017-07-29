package com.dpcoi.config.controller;/**
 * Created by liangzhifu
 * DATE:2017/6/17.
 */

import com.dpcoi.config.domain.DpcoiConfig;
import com.dpcoi.config.domain.DpcoiConfigVehicle;
import com.dpcoi.config.query.DpcoiConfigQuery;
import com.dpcoi.config.query.DpcoiConfigVehicleQuery;
import com.dpcoi.config.service.DpcoiConfigService;
import com.dpcoi.config.service.DpcoiConfigVehicleService;
import com.success.web.framework.util.AjaxUtil;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
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
@RequestMapping("/dpcoiConfigVehicle")
public class DpcoiConfigVehicleController {

    @Resource(name = "dpcoiConfigVehicleService")
    private DpcoiConfigVehicleService dpcoiConfigVehicleService;

    @RequestMapping("/getDpcoiConfigVehicleListDlg.do")
    public String getDpcoiConfigVehicleListDlg() throws Exception{
        return "dpcoi/dpcoiConfigVehicleList";
    }

    /**
     * 查询车型选项列表--分页
     * @param response 参数
     * @param dpcoiConfigVehicleQuery 查询条件
     */
    @RequestMapping("/getDpcoiConfigVehicleListPage.do")
    public void getDpcoiConfigVehicleListPage(HttpServletResponse response, DpcoiConfigVehicleQuery dpcoiConfigVehicleQuery){
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            List<Map<String, Object>> dpcoiConfigVehicleList = this.dpcoiConfigVehicleService.queryDpcoiConfigVehiclePageList(dpcoiConfigVehicleQuery);
            Integer dpcoiConfigVehicleCount = this.dpcoiConfigVehicleService.queryDpcoiConfigVehicleCount(dpcoiConfigVehicleQuery);
            Integer pageCount = dpcoiConfigVehicleCount / dpcoiConfigVehicleQuery.getSize() + (dpcoiConfigVehicleCount % dpcoiConfigVehicleQuery.getSize() > 0 ? 1 : 0);
            map.put("dpcoiConfigVehicleList", dpcoiConfigVehicleList);
            map.put("dpcoiConfigVehicleCount", dpcoiConfigVehicleCount);
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
     * 查询车型选项列表
     * @param response 参数
     * @param dpcoiConfigVehicleQuery 查询条件
     */
    @RequestMapping("/getDpcoiConfigVehicleList.do")
    public void getDpcoiConfigVehicleList(HttpServletResponse response, DpcoiConfigVehicleQuery dpcoiConfigVehicleQuery){
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            List<Map<String, Object>> dpcoiConfigVehicleList = this.dpcoiConfigVehicleService.queryDpcoiConfigVehicleList(dpcoiConfigVehicleQuery);
            map.put("dpcoiConfigVehicleList", dpcoiConfigVehicleList);
            map.put("success", true);
        }catch (Exception e){
            e.printStackTrace();
            map.put("success", false);
            map.put("message", e.getMessage());
        }
        AjaxUtil.ajaxResponse(response, new JSONObject(map).toString(), AjaxUtil.RESPONCE_TYPE_JSON);
    }

    /**
     * 新增车型选项
     * @param response 参数
     * @param dpcoiConfigVehicle 车型选项
     */
    @RequestMapping("/addDpcoiConfigVehicle.do")
    public void addDpcoiConfigVehicle(HttpServletResponse response, DpcoiConfigVehicle dpcoiConfigVehicle){
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            dpcoiConfigVehicle.setDeleteState("0");
            this.dpcoiConfigVehicleService.addDpcoiConfigVehicle(dpcoiConfigVehicle);
            map.put("success", true);
        }catch (Exception e){
            e.printStackTrace();
            map.put("success", false);
            map.put("message", e.getMessage());
        }
        AjaxUtil.ajaxResponse(response, new JSONObject(map).toString(), AjaxUtil.RESPONCE_TYPE_JSON);
    }

    /**
     * 删除车型选项
     * @param response 参数
     * @param dpcoiConfigVehicle 车型选项
     */
    @RequestMapping("deleteDpcoiConfigVehicle.do")
    public void deleteDpcoiConfigVehicle(HttpServletResponse response, DpcoiConfigVehicle dpcoiConfigVehicle){
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            this.dpcoiConfigVehicleService.deleteDpcoiConfigVehicle(dpcoiConfigVehicle);
            map.put("success", true);
        }catch (Exception e){
            e.printStackTrace();
            map.put("success", false);
            map.put("message", e.getMessage());
        }
        AjaxUtil.ajaxResponse(response, new JSONObject(map).toString(), AjaxUtil.RESPONCE_TYPE_JSON);
    }
}
