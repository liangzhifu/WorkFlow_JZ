package com.dpcoi.holiday.controller;/**
 * Created by liangzhifu
 * DATE:2017/6/4.
 */

import com.dpcoi.holiday.domain.Holiday;
import com.dpcoi.holiday.query.HolidayQuery;
import com.dpcoi.holiday.service.HolidayService;
import com.success.web.framework.util.AjaxUtil;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author lzf
 * @create 2017-06-04
 **/
@Controller
@RequestMapping("/holiday")
public class HolidayController {

    @Resource(name = "holidayService")
    private HolidayService holidayService;

    @RequestMapping("/getHolidayListDlg.do")
    public String getHolidayListDlg(HttpServletRequest request, Map<String, Object> model) throws Exception{
        return "dpcoi/holidayList";
    }

    /**
     * 获取节假日列表--分页
     * @param response 参数
     * @param holidayQuery 查询条件
     */
    @RequestMapping("getHolidayListPage.do")
    public void getHolidayListPage(HttpServletResponse response, HolidayQuery holidayQuery){
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            List<Map<String, Object>> holidayList = this.holidayService.queryHolidayListPage(holidayQuery);
            Integer holidayCount = this.holidayService.queryHolidayCount(holidayQuery);
            Integer pageCount = holidayCount / holidayQuery.getSize() + (holidayCount % holidayQuery.getSize() > 0 ? 1 : 0);
            map.put("holidayList", holidayList);
            map.put("holidayCount", holidayCount);
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
     * 新增节假日
     * @param request 参数
     * @param response 参数
     * @param holiday 节假日
     */
    @RequestMapping("/addHoliday.do")
    public String addDpcoiOrder(HttpServletRequest request, HttpServletResponse response, @ModelAttribute Holiday holiday){
        this.holidayService.addHoliday(holiday);
        return "dpcoi/holidayList";
    }

    /**
     * 删除节假日
     * @param request 参数
     * @param response 参数
     * @param holiday 节假日
     */
    @RequestMapping("/deleteHoliday.do")
    public void deleteHoliday(HttpServletRequest request, HttpServletResponse response, Holiday holiday){
        this.holidayService.deleteHoliday(holiday);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("success", true);
        AjaxUtil.ajaxResponse(response, new JSONObject(map).toString(), AjaxUtil.RESPONCE_TYPE_JSON);
    }

}
