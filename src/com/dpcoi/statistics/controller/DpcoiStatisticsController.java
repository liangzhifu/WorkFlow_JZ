package com.dpcoi.statistics.controller;/**
 * Created by liangzhifu
 * DATE:2017/5/18.
 */

import com.dpcoi.statistics.query.DpcoiStatisticsQuery;
import com.dpcoi.statistics.service.DpcoiStatisticsService;
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
 * @create 2017-05-18
 **/
@Controller
@RequestMapping("dpcoiStatistics")
public class DpcoiStatisticsController {

    @Resource(name = "dpcoiStatisticsService")
    private DpcoiStatisticsService dpcoiStatisticsService;

    @RequestMapping("getDpcoiStatisticsDlg.do")
    public String getDpcoiStatisticsDlg(){
        return "dpcoi/dpcoiStatistics";
    }

    /**
     * 获取Dpcoi统计数量
     * @param response 参数
     * @param dpcoiStatisticsQuery 查询条件
     */
    @RequestMapping("getDpcoiStatistics.do")
    public void getDpcoiStatistics(HttpServletResponse response, DpcoiStatisticsQuery dpcoiStatisticsQuery){
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            List<Map<String, Object>> dpcoiWoOrderCount = this.dpcoiStatisticsService.queryDpcoiWoOrderCount(dpcoiStatisticsQuery);
            Integer dpcoiOrderCount = this.dpcoiStatisticsService.queryDpcoiOrderCount(dpcoiStatisticsQuery);
            map.put("dpcoiOrderCount", dpcoiOrderCount);
            map.put("dpcoiWoOrderCount", dpcoiWoOrderCount);
            map.put("success", true);
        }catch (Exception e){
            e.printStackTrace();
            map.put("success", false);
            map.put("message", e.getMessage());
        }
        AjaxUtil.ajaxResponse(response, new JSONObject(map).toString(), AjaxUtil.RESPONCE_TYPE_JSON);
    }

    /**
     * 获取未完成工单列表
     * @param response 参数
     * @param dpcoiStatisticsQuery 查询条件
     */
    @RequestMapping("getWoOrderNoComplete.do")
    public void getWoOrderNoComplete(HttpServletResponse response, DpcoiStatisticsQuery dpcoiStatisticsQuery){
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            List<Map<String, Object>> orderList = this.dpcoiStatisticsService.queryDpcoiWoOrderNoComplete(dpcoiStatisticsQuery);
            map.put("orderList", orderList);
            map.put("success", true);
        }catch (Exception e){
            e.printStackTrace();
            map.put("success", false);
            map.put("message", e.getMessage());
        }
        AjaxUtil.ajaxResponse(response, new JSONObject(map).toString(), AjaxUtil.RESPONCE_TYPE_JSON);
    }

    /**
     * 获取已完成，超时的工单列表
     * @param response 参数
     * @param dpcoiStatisticsQuery 查询条件
     */
    @RequestMapping("getWoOrderCompleteDelay.do")
    public void getWoOrderCompleteDelay(HttpServletResponse response, DpcoiStatisticsQuery dpcoiStatisticsQuery){
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            List<Map<String, Object>> orderList = this.dpcoiStatisticsService.queryDpcoiWoOrderCompleteDelay(dpcoiStatisticsQuery);
            map.put("orderList", orderList);
            map.put("success", true);
        }catch (Exception e){
            e.printStackTrace();
            map.put("success", false);
            map.put("message", e.getMessage());
        }
        AjaxUtil.ajaxResponse(response, new JSONObject(map).toString(), AjaxUtil.RESPONCE_TYPE_JSON);
    }
}
