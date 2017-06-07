package com.dpcoi.woOrder.controller;

import com.dpcoi.order.query.DpcoiOrderQuery;
import com.dpcoi.order.service.DpcoiOrderService;
import com.dpcoi.woOrder.domain.DpcoiWoOrder;
import com.dpcoi.woOrder.query.DpcoiWoOrderQuery;
import com.dpcoi.woOrder.service.DpcoiWoOrderService;
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
 * Created by 梁志福 on 2017/4/20.
 */
@Controller
@RequestMapping("/dpcoiWoOrder")
public class DpcoiWoOrderController {

    @Resource(name = "dpcoiWoOrderService")
    private DpcoiWoOrderService dpcoiWoOrderService;

    @RequestMapping("/getDpcoiWoOrderListDlg.do")
    public String getDpcoiWoOrderListDlg(){
        return "dpcoi/dpcoiWoOrderList";
    }

    /**
     * 获取代办任务列表--分页
     * @param request 参数
     * @param response 参数
     * @param dpcoiWoOrderQuery 查询条件
     */
    @RequestMapping("getDpcoiWoOrderListPage.do")
    public void getWoDpcoiUsrListPage(HttpServletRequest request, HttpServletResponse response, DpcoiWoOrderQuery dpcoiWoOrderQuery){
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            User user = (User)request.getSession().getAttribute(Constant.STAFF_KEY);
            dpcoiWoOrderQuery.setDpcoiUserId(user.getUserId());
            List<Map<String, Object>> dpcoiWoOrderList = this.dpcoiWoOrderService.queryDpcoiWoOrderListPage(dpcoiWoOrderQuery);
            Integer dpcoiWoOrderCount = this.dpcoiWoOrderService.queryDpcoiWoOrderCount(dpcoiWoOrderQuery);
            Integer pageCount = dpcoiWoOrderCount / dpcoiWoOrderQuery.getSize() + (dpcoiWoOrderCount % dpcoiWoOrderQuery.getSize() > 0 ? 1 : 0);
            map.put("dpcoiWoOrderList", dpcoiWoOrderList);
            map.put("dpcoiWoOrderCount", dpcoiWoOrderCount);
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
     * 确认变更
     * @param request 参数
     * @param response 参数
     * @param dpcoiWoOrder 工单
     * @param confirmResult 确认结果
     */
    @RequestMapping("confirmDpcoiWoOrder.do")
    public void confirmDpcoiWoOrder(HttpServletRequest request, HttpServletResponse response, DpcoiWoOrder dpcoiWoOrder, Integer confirmResult){
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            User user = (User)request.getSession().getAttribute(Constant.STAFF_KEY);
            this.dpcoiWoOrderService.editWoOrderConfirm(dpcoiWoOrder, confirmResult, user);
            map.put("success", true);
        }catch (Exception e){
            e.printStackTrace();
            map.put("success", false);
            map.put("message", e.getMessage());
        }
        AjaxUtil.ajaxResponse(response, new JSONObject(map).toString(), AjaxUtil.RESPONCE_TYPE_JSON);
    }

    /**
     * 变更文件上传完成
      * @param request 参数
     * @param response 参数
     * @param dpcoiWoOrder 工单
     */
    @RequestMapping("fileCompleteDpcoiWoOrder.do")
    public void fileCompleteDpcoiWoOrder(HttpServletRequest request, HttpServletResponse response, DpcoiWoOrder dpcoiWoOrder){
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            User user = (User)request.getSession().getAttribute(Constant.STAFF_KEY);
            this.dpcoiWoOrderService.editWoOrderFileComplete(dpcoiWoOrder, user);
            map.put("success", true);
        }catch (Exception e){
            e.printStackTrace();
            map.put("success", false);
            map.put("message", e.getMessage());
        }
        AjaxUtil.ajaxResponse(response, new JSONObject(map).toString(), AjaxUtil.RESPONCE_TYPE_JSON);
    }

    /**
     * 变更审核
     * @param request 参数
     * @param response 参数
     * @param dpcoiWoOrder 工单
     * @param confirmResult 审核结果
     */
    @RequestMapping("managerConfirmDpcoiWoOrder.do")
    public void managerConfirmDpcoiWoOrder(HttpServletRequest request, HttpServletResponse response, DpcoiWoOrder dpcoiWoOrder, Integer confirmResult, String noPassedWoOrderFileIdStr){
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            User user = (User)request.getSession().getAttribute(Constant.STAFF_KEY);
            this.dpcoiWoOrderService.editWoOrderManagerConfirm(dpcoiWoOrder, confirmResult, user, noPassedWoOrderFileIdStr);
            map.put("success", true);
        }catch (Exception e){
            e.printStackTrace();
            map.put("success", false);
            map.put("message", e.getMessage());
        }
        AjaxUtil.ajaxResponse(response, new JSONObject(map).toString(), AjaxUtil.RESPONCE_TYPE_JSON);
    }


}
