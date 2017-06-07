package com.other.history.controller;/**
 * Created by liangzhifu
 * DATE:2017/5/12.
 */

import com.other.history.query.OperateHistoryQuery;
import com.other.history.service.OperateHistoryService;
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
 * @create 2017-05-12
 **/
@Controller
@RequestMapping("/dpcoiOrder")
public class OperateHistoryController {

    @Resource(name = "operateHistoryService")
    private OperateHistoryService operateHistoryService;

    /**
     * 查询操作历史记录
     * @param response 参数
     * @param operateHistoryQuery 查询条件
     */
    @RequestMapping("getOperateHistoryList.do")
    public void getOperateHistoryList(HttpServletResponse response, OperateHistoryQuery operateHistoryQuery){
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            List<Map<String, Object>> operateHistoryList = this.operateHistoryService.queryOperateHistoryList(operateHistoryQuery);
            map.put("operateHistoryList", operateHistoryList);
            map.put("success", true);
        }catch (Exception e){
            e.printStackTrace();
            map.put("success", false);
            map.put("message", e.getMessage());
        }
        AjaxUtil.ajaxResponse(response, new JSONObject(map).toString(), AjaxUtil.RESPONCE_TYPE_JSON);
    }
}
