package com.dpcoi.woOrder.controller;/**
 * Created by liangzhifu
 * DATE:2017/6/30.
 */

import com.dpcoi.woOrder.query.RRProblemWoOrderQuery;
import com.dpcoi.woOrder.service.RRProblemWoOrderService;
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
 *
 * @author lzf
 * @create 2017-06-30
 **/
@Controller
@RequestMapping("/rrProblemWoOrder")
public class RRProblemWoOrderController {

    @Resource(name = "rRProblemWoOrderService")
    private RRProblemWoOrderService rRProblemWoOrderService;

    @RequestMapping("/getRRProblemWoOrderListDlg.do")
    public String getRRProblemWoOrderListDlg(){
        return "dpcoi/rrProblemWoOrderList";
    }

    /**
     * 获取RR问题点代办任务列表--分页
     * @param request 参数
     * @param response 参数
     * @param rrProblemWoOrderQuery 查询条件
     */
    @RequestMapping("getRRProblemWoOrderListPage.do")
    public void getWoRRProblemUsrListPage(HttpServletRequest request, HttpServletResponse response, RRProblemWoOrderQuery rrProblemWoOrderQuery){
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            User user = (User)request.getSession().getAttribute(Constant.STAFF_KEY);
            rrProblemWoOrderQuery.setDpcoiUserId(user.getUserId());
            List<Map<String, Object>> rrProblemWoOrderList = this.rRProblemWoOrderService.queryRRProblemWoOrderListPage(rrProblemWoOrderQuery);
            Integer rrProblemWoOrderCount = this.rRProblemWoOrderService.queryRRProblemWoOrderCount(rrProblemWoOrderQuery);
            Integer pageCount = rrProblemWoOrderCount / rrProblemWoOrderQuery.getSize() + (rrProblemWoOrderCount % rrProblemWoOrderQuery.getSize() > 0 ? 1 : 0);
            map.put("rrProblemWoOrderList", rrProblemWoOrderList);
            map.put("rrProblemWoOrderCount", rrProblemWoOrderCount);
            map.put("pageCount", pageCount);
            map.put("success", true);
        }catch (Exception e){
            e.printStackTrace();
            map.put("success", false);
            map.put("message", e.getMessage());
        }
        AjaxUtil.ajaxResponse(response, new JSONObject(map).toString(), AjaxUtil.RESPONCE_TYPE_JSON);
    }

}
