package com.dpcoi.order.controller;

import com.dpcoi.order.domain.DpcoiOrder;
import com.dpcoi.order.query.DpcoiOrderQuery;
import com.dpcoi.order.service.DpcoiOrderService;
import com.dpcoi.rr.domain.RRProblem;
import com.dpcoi.rr.service.RRProblemService;
import com.other.history.query.OperateHistoryQuery;
import com.other.history.service.OperateHistoryService;
import com.success.common.Constant;
import com.success.sys.user.domain.User;
import com.success.sys.user.service.UserService;
import com.success.web.framework.util.AjaxUtil;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 梁志福 on 2017/4/20.
 */
@Controller
@RequestMapping("/dpcoiOrder")
public class DpcoiOrderController {

    @Resource(name = "dpcoiOrderService")
    private DpcoiOrderService dpcoiOrderService;

    @Resource(name = "userService")
    private UserService userService;

    @Resource(name = "rRProblemService")
    private RRProblemService rRProblemService;

    @Resource(name = "operateHistoryService")
    private OperateHistoryService operateHistoryService;

    @RequestMapping("/getDpcoiOrderListDlg.do")
    public String getDpcoiOrderDlg(HttpServletRequest request, Map<String, Object> model) throws Exception{
        User user = (User)request.getSession().getAttribute(Constant.STAFF_KEY);
        Integer dpcoiAddJurisdiction = this.dpcoiOrderService.queryDpcoiAddJurisdiction(user);
        model.put("dpcoiAddJurisdiction", dpcoiAddJurisdiction);
        model.put("userId", user.getUserId());
        return "dpcoi/dpcoiOrderList";
    }

    @RequestMapping("/getDpcoiOrderAddDlg.do")
    public String getDpcoiOrderAddDlg(HttpServletRequest request, Map<String, Object> model){
        User user = (User)request.getSession().getAttribute(Constant.STAFF_KEY);
        model.put("action", "add");
        model.put("dpcoiOrder", null);
        model.put("user", new JSONObject(user));
        return "dpcoi/dpcoiOrderEdit";
    }

    @RequestMapping("/getDpcoiOrderDetailDlg.do")
    public String getDpcoiOrderDetailDlg(Map<String, Object> model, Integer dpcoiOrderId) throws Exception{
        DpcoiOrder dpcoiOrder = new DpcoiOrder();
        dpcoiOrder.setDpcoiOrderId(dpcoiOrderId);
        List<Map<String, Object>> dpcoiWoOrderDetailList = this.dpcoiOrderService.queryWoOrderDetailList(dpcoiOrder);
        model.put("dpcoiWoOrderDetailList", dpcoiWoOrderDetailList);
        dpcoiOrder = this.dpcoiOrderService.queryDpcoiOrder(dpcoiOrder);
        model.put("dpcoiOrder", dpcoiOrder);
        Integer rrProblemId = dpcoiOrder.getRrProblemId();
        if(rrProblemId != null){
            RRProblem rrProblem = new RRProblem();
            rrProblem.setId(rrProblemId);
            rrProblem = this.rRProblemService.queryRRProblem(rrProblem);
            model.put("rrProblem", rrProblem);
        }else {
            model.put("rrProblem", null);
        }
        OperateHistoryQuery operateHistoryQuery = new OperateHistoryQuery();
        operateHistoryQuery.setSystermType(1);
        operateHistoryQuery.setBusinessType(1);
        operateHistoryQuery.setBusinessId(dpcoiOrderId);
        List<Map<String, Object>> operateHistoryList = this.operateHistoryService.queryOperateHistoryList(operateHistoryQuery);
        model.put("operateHistoryList", operateHistoryList);
        return "dpcoi/dpcoiOrderDetail";
    }

    @RequestMapping("/getDpcoiOrderEditDlg.do")
    public String getDpcoiOrderEditDlg(HttpServletRequest request, Map<String, Object> model, DpcoiOrder dpcoiOrder) throws Exception{
        User user = (User)request.getSession().getAttribute(Constant.STAFF_KEY);
        dpcoiOrder = this.dpcoiOrderService.queryDpcoiOrder(dpcoiOrder);
        if(dpcoiOrder.getPfmeaConfirmDate() == null){
            model.put("action", "editAll");
        }else {
            model.put("action", "edit");
        }
        model.put("user", new JSONObject(user));
        model.put("dpcoiOrder", new JSONObject(dpcoiOrder));
        return "dpcoi/dpcoiOrderEdit";
    }

    /**
     * 获取定单列表--分页
     * @param response 参数
     * @param dpcoiOrderQuery 查询条件
     */
    @RequestMapping("getDpcoiOrderListPage.do")
    public void getDpcoiUsrListPage(HttpServletResponse response, DpcoiOrderQuery dpcoiOrderQuery){
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            List<Map<String, Object>> dpcoiOrderList = this.dpcoiOrderService.queryDpcoiOrderListPage(dpcoiOrderQuery);
            Integer dpcoiOrderCount = this.dpcoiOrderService.queryDpcoiOrderCount(dpcoiOrderQuery);
            Integer pageCount = dpcoiOrderCount / dpcoiOrderQuery.getSize() + (dpcoiOrderCount % dpcoiOrderQuery.getSize() > 0 ? 1 : 0);
            map.put("dpcoiOrderList", dpcoiOrderList);
            map.put("dpcoiOrderCount", dpcoiOrderCount);
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
     * 新增定单
     * @param request 参数
     * @param response 参数
     * @param dpcoiOrder 定单
     */
    @RequestMapping("/addDpcoiOrder.do")
    public void addDpcoiOrder(HttpServletRequest request, HttpServletResponse response, @ModelAttribute DpcoiOrder dpcoiOrder){
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            User user = (User)request.getSession().getAttribute(Constant.STAFF_KEY);
            dpcoiOrder.setDpcoiOrderType(1);
            dpcoiOrder.setDelFlag("0");
            dpcoiOrder.setDpcoiOrderState(1);
            dpcoiOrder.setCreateDate(new Date());
            dpcoiOrder.setCreateBy(user.getUserId());
            dpcoiOrder.setUpdateDate(new Date());
            dpcoiOrder.setUpdateBy(user.getUserId());
            dpcoiOrder.setPfmeaCreateDate(new Date());
            dpcoiOrder.setPfmeaEmailDate(new Date());
            dpcoiOrder.setPfmeaDelay(0);
            dpcoiOrder.setCpDelay(0);
            dpcoiOrder.setStandardBookDelay(0);
            DpcoiOrderQuery dpcoiOrderQuery = new DpcoiOrderQuery();
            dpcoiOrderQuery.setDesignChangeNo(dpcoiOrder.getDesignChangeNo());
            dpcoiOrderQuery.setIssuedNo(dpcoiOrder.getIssuedNo());
            Integer num = this.dpcoiOrderService.querySameDpcoiOrder(dpcoiOrderQuery);
            if(num > 0){
                map.put("success", false);
                map.put("message", "存在相同的《设变通知书》编号或者设变号的DPCOI定单！");
            }else {
                this.dpcoiOrderService.addDpcoiOrder(dpcoiOrder);
                map.put("success", true);
            }
        }catch (Exception e){
            e.printStackTrace();
            map.put("success", false);
            map.put("message", e.getMessage());
        }
        AjaxUtil.ajaxResponse(response, new JSONObject(map).toString(), AjaxUtil.RESPONCE_TYPE_JSON);
    }

    @RequestMapping("/editDpcoiOrder.do")
    public void editDpcoiOrder(HttpServletRequest request, HttpServletResponse response, @ModelAttribute DpcoiOrder dpcoiOrder){
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            User user = (User)request.getSession().getAttribute(Constant.STAFF_KEY);
            DpcoiOrderQuery dpcoiOrderQuery = new DpcoiOrderQuery();
            dpcoiOrderQuery.setDesignChangeNo(dpcoiOrder.getDesignChangeNo());
            dpcoiOrderQuery.setIssuedNo(dpcoiOrder.getIssuedNo());
            dpcoiOrderQuery.setDpcoiOrderId(dpcoiOrder.getDpcoiOrderId());
            Integer num = this.dpcoiOrderService.querySameDpcoiOrder(dpcoiOrderQuery);
            if(num > 0){
                map.put("success", false);
                map.put("message", "存在相同的《设变通知书》编号或者设变号的DPCOI定单！");
            }else {
                this.dpcoiOrderService.editDpcoiOrder(dpcoiOrder, user);
                map.put("success", true);
            }
        }catch (Exception e){
            e.printStackTrace();
            map.put("success", false);
            map.put("message", e.getMessage());
        }
        AjaxUtil.ajaxResponse(response, new JSONObject(map).toString(), AjaxUtil.RESPONCE_TYPE_JSON);
    }

    /**
     * 作废定单
     * @param request 参数
     * @param response 参数
     * @param dpcoiOrder 定单实体ID
     */
    @RequestMapping("/deleteDpcoiOrder.do")
    public void deleteDpcoiOrder(HttpServletRequest request, HttpServletResponse response, DpcoiOrder dpcoiOrder){
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            User user = (User)request.getSession().getAttribute(Constant.STAFF_KEY);
            this.dpcoiOrderService.editDpcoiOrderToVoid(dpcoiOrder, user);
            map.put("success", true);
        }catch (Exception e){
            e.printStackTrace();
            map.put("success", false);
            map.put("message", e.getMessage());
        }
        AjaxUtil.ajaxResponse(response, new JSONObject(map).toString(), AjaxUtil.RESPONCE_TYPE_JSON);
    }

    /**
     * 作废定单
     * @param request 参数
     * @param response 参数
     * @param dpcoiOrder 定单实体ID
     */
    @RequestMapping("/toVoidDpcoiOrder.do")
    public void toVoidDpcoiOrder(HttpServletRequest request, HttpServletResponse response, DpcoiOrder dpcoiOrder){
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            User user = (User)request.getSession().getAttribute(Constant.STAFF_KEY);
            this.dpcoiOrderService.editDpcoiOrderToVoid(dpcoiOrder, user);
            map.put("success", true);
        }catch (Exception e){
            e.printStackTrace();
            map.put("success", false);
            map.put("message", e.getMessage());
        }
        AjaxUtil.ajaxResponse(response, new JSONObject(map).toString(), AjaxUtil.RESPONCE_TYPE_JSON);
    }
}
