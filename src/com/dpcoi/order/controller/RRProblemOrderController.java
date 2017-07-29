package com.dpcoi.order.controller;/**
 * Created by liangzhifu
 * DATE:2017/6/29.
 */

import com.dpcoi.order.domain.DpcoiOrder;
import com.dpcoi.order.query.RRProblemOrderQuery;
import com.dpcoi.order.service.DpcoiOrderService;
import com.dpcoi.order.service.RRProblemOrderService;
import com.dpcoi.rr.domain.RRProblem;
import com.dpcoi.rr.service.RRProblemService;
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
 * @create 2017-06-29
 **/
@Controller
@RequestMapping("/rrProblemOrder")
public class RRProblemOrderController {

    @Resource(name = "rRProblemOrderService")
    private RRProblemOrderService rRProblemOrderService;

    @Resource(name = "dpcoiOrderService")
    private DpcoiOrderService dpcoiOrderService;

    @Resource(name = "rRProblemService")
    private RRProblemService rRProblemService;

    @Resource(name = "operateHistoryService")
    private OperateHistoryService operateHistoryService;

    @RequestMapping("/getRRProblemOrderListDlg.do")
    public String getRRProblemOrderDlg() throws Exception{
        return "dpcoi/rrProblemOrderList";
    }

    /**
     * 获取RR问题点定单列表--分页
     * @param response 参数
     * @param rrProblemOrderQuery 查询条件
     */
    @RequestMapping("getRRProblemOrderListPage.do")
    public void getRRProblemOrderListPage(HttpServletResponse response, RRProblemOrderQuery rrProblemOrderQuery){
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            List<Map<String, Object>> rrProblemOrderList = this.rRProblemOrderService.queryRRProblemOrderListPage(rrProblemOrderQuery);
            Integer rrProblemOrderCount = this.rRProblemOrderService.queryRRProblemOrderCount(rrProblemOrderQuery);
            Integer pageCount = rrProblemOrderCount / rrProblemOrderQuery.getSize() + (rrProblemOrderCount % rrProblemOrderQuery.getSize() > 0 ? 1 : 0);
            map.put("rrProblemOrderList", rrProblemOrderList);
            map.put("rrProblemOrderCount", rrProblemOrderCount);
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
     * 获取RR问题点订单详情
     * @param model 参数
     * @param rrProblemOrderId 订单ID
     * @return 返回结果
     * @throws Exception 异常
     */
    @RequestMapping("/getRRProblemOrderDetailDlg.do")
    public String getRRProblemOrderDetailDlg(Map<String, Object> model, Integer rrProblemOrderId) throws Exception{
        DpcoiOrder dpcoiOrder = new DpcoiOrder();
        dpcoiOrder.setDpcoiOrderId(rrProblemOrderId);
        List<Map<String, Object>> dpcoiWoOrderDetailList = this.dpcoiOrderService.queryWoOrderDetailList(dpcoiOrder);
        model.put("dpcoiWoOrderDetailList", dpcoiWoOrderDetailList);
        dpcoiOrder = this.dpcoiOrderService.queryDpcoiOrder(dpcoiOrder);
        model.put("dpcoiOrder", dpcoiOrder);
        Integer rrProblemId = dpcoiOrder.getRrProblemId();
        RRProblem rrProblem = new RRProblem();
        rrProblem.setId(rrProblemId);
        rrProblem = this.rRProblemService.queryRRProblem(rrProblem);
        model.put("rrProblem", rrProblem);
        OperateHistoryQuery operateHistoryQuery = new OperateHistoryQuery();
        operateHistoryQuery.setSystermType(1);
        operateHistoryQuery.setBusinessType(1);
        operateHistoryQuery.setBusinessId(rrProblemOrderId);
        List<Map<String, Object>> operateHistoryList = this.operateHistoryService.queryOperateHistoryList(operateHistoryQuery);
        model.put("operateHistoryList", operateHistoryList);
        return "dpcoi/rrProblemOrderDetail";
    }
}
