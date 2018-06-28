package com.dpcoi.order.controller;

import com.dpcoi.order.query.DpcoiOrderRollBackQuery;
import com.dpcoi.order.service.DpcoiOrderRollBackService;
import com.success.common.Constant;
import com.success.sys.user.domain.User;
import com.success.web.framework.util.AjaxUtil;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lzf
 * @create 2017-10-23
 **/
@Controller
@RequestMapping(value = "dpcoiOrderRollBack")
public class DpcoiOrderRollBackController {

    @Resource(name = "dpcoiOrderRollBackService")
    private DpcoiOrderRollBackService dpcoiOrderRollBackService;

    @RequestMapping("/getDpcoiOrderRollBackDlg.do")
    public String getDpcoiOrderRollBackDlg() throws Exception{
        return "dpcoi/dpcoiOrderRollBackList";
    }

    @RequestMapping("/getRRProblemOrderRollBackDlg.do")
    public String getRRProblemOrderRollBackDlg() throws Exception{
        return "dpcoi/rrProblemOrderRollBackList";
    }

    /**
     * 查询可以回滚的DPCOI列表
     * @param response 参数
     * @param dpcoiOrderRollBackQuery 查询条件
     */
    @RequestMapping("getDpcoiOrderRollBackListPage.do")
    public void getDpcoiOrderRollBackListPage(HttpServletResponse response, DpcoiOrderRollBackQuery dpcoiOrderRollBackQuery){
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            List<Map<String, Object>> dpcoiOrderRollBackList = this.dpcoiOrderRollBackService.queryDpcoiOrderRollBackllListPage(dpcoiOrderRollBackQuery);
            Integer dpcoiOrderRollBackCount = this.dpcoiOrderRollBackService.queryDpcoiOrderRollBackCount(dpcoiOrderRollBackQuery);
            Integer pageCount = dpcoiOrderRollBackCount / dpcoiOrderRollBackQuery.getSize() + (dpcoiOrderRollBackCount % dpcoiOrderRollBackQuery.getSize() > 0 ? 1 : 0);
            map.put("dpcoiOrderRollBackList", dpcoiOrderRollBackList);
            map.put("dpcoiOrderRollBackCount", dpcoiOrderRollBackCount);
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
     * 查询可以回滚的RR问题点的DPCOI列表
     * @param response 参数
     * @param dpcoiOrderRollBackQuery 查询条件
     */
    @RequestMapping("getRRProblemOrderRollBackListPage.do")
    public void getRRProblemOrderRollBackListPage(HttpServletResponse response, DpcoiOrderRollBackQuery dpcoiOrderRollBackQuery){
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            List<Map<String, Object>> rrProblemOrderRollBackList = this.dpcoiOrderRollBackService.queryRRProblemOrderRollBackllListPage(dpcoiOrderRollBackQuery);
            Integer rrProblemOrderRollBackCount = this.dpcoiOrderRollBackService.queryRRProblemOrderRollBackCount(dpcoiOrderRollBackQuery);
            Integer pageCount = rrProblemOrderRollBackCount / dpcoiOrderRollBackQuery.getSize() + (rrProblemOrderRollBackCount % dpcoiOrderRollBackQuery.getSize() > 0 ? 1 : 0);
            map.put("rrProblemOrderRollBackList", rrProblemOrderRollBackList);
            map.put("rrProblemOrderRollBackCount", rrProblemOrderRollBackCount);
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
     * 回滚RR问题点的DPCOI
     * @param response 参数
     * @param dpcoiOrderId 订单ID
     * @param dpcoiWoOrderType 工单类型
     */
    @RequestMapping("rollBackDpcoiOrder.do")
    public void rollBackDpcoiOrder(HttpServletResponse response, Integer dpcoiOrderId, Integer dpcoiWoOrderType){
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            this.dpcoiOrderRollBackService.updateRollBackDpcoiOrder(dpcoiOrderId, dpcoiWoOrderType);
            map.put("success", true);
        }catch (Exception e){
            e.printStackTrace();
            map.put("success", false);
            map.put("message", e.getMessage());
        }
        AjaxUtil.ajaxResponse(response, new JSONObject(map).toString(), AjaxUtil.RESPONCE_TYPE_JSON);
    }
}
