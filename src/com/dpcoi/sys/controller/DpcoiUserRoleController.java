package com.dpcoi.sys.controller;/**
 * Created by liangzhifu
 * DATE:2017/5/3.
 */

import com.dpcoi.sys.query.DpcoiUserRoleQuery;
import com.dpcoi.sys.service.DpcoiUserRoleService;
import com.dpcoi.sys.service.DpcoiUserService;
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
 * @create 2017-05-03
 **/
@Controller
@RequestMapping("/dpcoiUserRole")
public class DpcoiUserRoleController {

    @Resource(name = "dpcoiUserRoleService")
    private DpcoiUserRoleService dpcoiUserRoleService;

    /**
     * 获取dpcoi权限列表
     * @param response 参数
     * @param dpcoiUserRoleQuery 查询条件
     */
    @RequestMapping("getDpcoiRoleList.do")
    public void getDpcoiRoleList(HttpServletResponse response, DpcoiUserRoleQuery dpcoiUserRoleQuery){
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            List<Map<String, Object>> roleList = this.dpcoiUserRoleService.queryDpcoiRoleList(dpcoiUserRoleQuery);
            map.put("roleList", roleList);
            map.put("success", true);
        }catch (Exception e){
            e.printStackTrace();
            map.put("success", false);
            map.put("message", e.getMessage());
        }
        AjaxUtil.ajaxResponse(response, new JSONObject(map).toString(), AjaxUtil.RESPONCE_TYPE_JSON);
    }

    /**
     * 修改dpcoi用户权限
     *  @param request 参数
     * @param response 参数
     * @param dpcoiRoleIdStr 权限列表
     * @param dpcoiUserId dpcoi用户ID
     */
    @RequestMapping("/editDpcoiUserRole.do")
    public void editDpcoiUserRole(HttpServletRequest request, HttpServletResponse response, String dpcoiRoleIdStr, String dpcoiUserId){
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            User user = (User)request.getSession().getAttribute(Constant.STAFF_KEY);
            this.dpcoiUserRoleService.addDpcoiUserRoles(dpcoiRoleIdStr, dpcoiUserId, user);
            map.put("success", true);
        }catch (Exception e){
            e.printStackTrace();
            map.put("success", false);
            map.put("message", e.getMessage());
        }
        AjaxUtil.ajaxResponse(response, new JSONObject(map).toString(), AjaxUtil.RESPONCE_TYPE_JSON);
    }
}
