package com.dpcoi.statistics.controller;/**
 * Created by liangzhifu
 * DATE:2017/7/20.
 */

import com.dpcoi.statistics.query.RRDelayStatisticsQuery;
import com.dpcoi.statistics.service.RRDelayStatisticsService;
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
 * @create 2017-07-20
 **/
@Controller
@RequestMapping("rrDelayStatistics")
public class RRDelayStatisticsController {

    @Resource(name = "rRDelayStatisticsService")
    private RRDelayStatisticsService rRDelayStatisticsService;

    @RequestMapping("getRRDelayStatisticsDlg.do")
    public String getRRDelayStatisticsDlg(){
        return "dpcoi/rrDelayStatistics";
    }

    /**
     * 获取RR问题延时统计数量
     * @param response 参数
     * @param rrDelayStatisticsQuery 查询条件
     */
    @RequestMapping("getStatisticsCount.do")
    public void getStatisticsCount(HttpServletResponse response, RRDelayStatisticsQuery rrDelayStatisticsQuery){
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            Integer statisticsCount = this.rRDelayStatisticsService.queryStatisticsCount(rrDelayStatisticsQuery);
            map.put("statisticsCount", statisticsCount);
            map.put("success", true);
        }catch (Exception e){
            e.printStackTrace();
            map.put("success", false);
            map.put("message", e.getMessage());
        }
        AjaxUtil.ajaxResponse(response, new JSONObject(map).toString(), AjaxUtil.RESPONCE_TYPE_JSON);
    }

    /**
     * 获取RR问题延时分组（按责任人分组）统计数量
     * @param response 参数
     * @param rrDelayStatisticsQuery 查询条件
     */
    @RequestMapping("getStatisticsList.do")
    public void getStatisticsList(HttpServletResponse response, RRDelayStatisticsQuery rrDelayStatisticsQuery){
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            List<Map<String, Object>> mapList = this.rRDelayStatisticsService.queryStatisticsList(rrDelayStatisticsQuery);
            map.put("mapList", mapList);
            map.put("success", true);
        }catch (Exception e){
            e.printStackTrace();
            map.put("success", false);
            map.put("message", e.getMessage());
        }
        AjaxUtil.ajaxResponse(response, new JSONObject(map).toString(), AjaxUtil.RESPONCE_TYPE_JSON);
    }

    /**
     * 获取RR问题延时统计数量
     * @param response 参数
     * @param rrDelayStatisticsQuery 查询条件
     */
    @RequestMapping("getRRDelayStatisticsList.do")
    public void getRRDelayStatisticsList(HttpServletResponse response, RRDelayStatisticsQuery rrDelayStatisticsQuery){
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            List<Map<String, Object>> mapList = this.rRDelayStatisticsService.queryRRDelayStatisticsList(rrDelayStatisticsQuery);
            map.put("mapList", mapList);
            map.put("success", true);
        }catch (Exception e){
            e.printStackTrace();
            map.put("success", false);
            map.put("message", e.getMessage());
        }
        AjaxUtil.ajaxResponse(response, new JSONObject(map).toString(), AjaxUtil.RESPONCE_TYPE_JSON);
    }

}
