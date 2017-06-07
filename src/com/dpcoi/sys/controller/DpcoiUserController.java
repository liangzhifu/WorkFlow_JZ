package com.dpcoi.sys.controller;

import com.dpcoi.sys.domain.DpcoiUser;
import com.dpcoi.sys.query.DpcoiUserQuery;
import com.dpcoi.sys.service.DpcoiUserService;
import com.success.common.Constant;
import com.success.sys.user.domain.User;
import com.success.sys.user.query.UserQuery;
import com.success.web.framework.util.AjaxUtil;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
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
@RequestMapping("/dpcoiUser")
public class DpcoiUserController {

    @Resource(name = "dpcoiUserService")
    private DpcoiUserService dpcoiUserService;

    @RequestMapping("/getDpcoiUserListDlg.do")
    public String getDpcoiUserDlg(){
        return "dpcoi/dpcoiUserList";
    }

    /**
     * 获取dpcoi系统用户--分页
     * @param response 参数
     * @param dpcoiUserQuery 查询条件
     */
    @RequestMapping("getDpcoiUserListPage.do")
    public void getDpcoiUserListPage(HttpServletResponse response, DpcoiUserQuery dpcoiUserQuery){
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            List<Map<String, Object>> dpcoiUserList = this.dpcoiUserService.queryDpcoiUserListPage(dpcoiUserQuery);
            Integer dpcoiUserCount = this.dpcoiUserService.queryDpcoiUserCount(dpcoiUserQuery);
            Integer pageCount = dpcoiUserCount / dpcoiUserQuery.getSize() + (dpcoiUserCount % dpcoiUserQuery.getSize() > 0 ? 1 : 0);
            map.put("dpcoiUserList", dpcoiUserList);
            map.put("dpcoiUserCount", dpcoiUserCount);
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
     * 获取未加入dpcoi系统的用户
     * @param response 参数
     * @param userQuery 查询条件
     */
    @RequestMapping("getNoDpcoiUserList.do")
    public void getNoDpcoiUserList(HttpServletResponse response, UserQuery userQuery){
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            List<Map<String, Object>> userList = this.dpcoiUserService.queryNoDpcoiUserList(userQuery);
            map.put("userList", userList);
            map.put("success", true);
        }catch (Exception e){
            e.printStackTrace();
            map.put("success", false);
            map.put("message", e.getMessage());
        }
        AjaxUtil.ajaxResponse(response, new JSONObject(map).toString(), AjaxUtil.RESPONCE_TYPE_JSON);
    }

    /**
     * Dpcoi系统添加用户
     * @param request 参数
     * @param response 参数
     * @param userIdStr 用户ID字符串
     */
    @RequestMapping("/addDpcoiUser.do")
    public void addDpcoiUser(HttpServletRequest request, HttpServletResponse response, String userIdStr){
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            User user = (User)request.getSession().getAttribute(Constant.STAFF_KEY);
            this.dpcoiUserService.addDpcoiUsers(userIdStr, user);
            map.put("success", true);
        }catch (Exception e){
            e.printStackTrace();
            map.put("success", false);
            map.put("message", e.getMessage());
        }
        AjaxUtil.ajaxResponse(response, new JSONObject(map).toString(), AjaxUtil.RESPONCE_TYPE_JSON);
    }

    /**
     * 删除dpcoi系统用户
     * @param request 参数
     * @param response 参数
     * @param dpcoiUser 用户ID
     */
    @RequestMapping("/deleteDpcoiUser.do")
    public void deleteDpcoiUser(HttpServletRequest request, HttpServletResponse response, DpcoiUser dpcoiUser){
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            User user = (User)request.getSession().getAttribute(Constant.STAFF_KEY);
            dpcoiUser.setDelFlag("1");
            dpcoiUser.setUpdateBy(user.getUserId());
            dpcoiUser.setUpdateDate(new Date());
            this.dpcoiUserService.updateDpcoiUser(dpcoiUser);
            map.put("success", true);
        }catch (Exception e){
            e.printStackTrace();
            map.put("success", false);
            map.put("message", e.getMessage());
        }
        AjaxUtil.ajaxResponse(response, new JSONObject(map).toString(), AjaxUtil.RESPONCE_TYPE_JSON);
    }

    /**
     * 开启dpcoi用户的所有权限
     * @param request 参数
     * @param response 参数
     * @param dpcoiUser 用户ID
     */
    @RequestMapping("/openDpcoiUser.do")
    public void openDpcoiUser(HttpServletRequest request, HttpServletResponse response, DpcoiUser dpcoiUser){
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            User user = (User)request.getSession().getAttribute(Constant.STAFF_KEY);
            dpcoiUser.setUpdateBy(user.getUserId());
            dpcoiUser.setUpdateDate(new Date());
            dpcoiUser.setDpcoiUserState("1");
            this.dpcoiUserService.updateDpcoiUser(dpcoiUser);
            map.put("success", true);
        }catch (Exception e){
            e.printStackTrace();
            map.put("success", false);
            map.put("message", e.getMessage());
        }
        AjaxUtil.ajaxResponse(response, new JSONObject(map).toString(), AjaxUtil.RESPONCE_TYPE_JSON);
    }

    /**
     * 关闭dpcoi用户的所有权限
     * @param request 参数
     * @param response 参数
     * @param dpcoiUser 用户ID
     */
    @RequestMapping("/closeDpcoiUser.do")
    public void closeDpcoiUser(HttpServletRequest request, HttpServletResponse response, DpcoiUser dpcoiUser){
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            User user = (User)request.getSession().getAttribute(Constant.STAFF_KEY);
            dpcoiUser.setUpdateBy(user.getUserId());
            dpcoiUser.setUpdateDate(new Date());
            dpcoiUser.setDpcoiUserState("0");
            this.dpcoiUserService.updateDpcoiUser(dpcoiUser);
            map.put("success", true);
        }catch (Exception e){
            e.printStackTrace();
            map.put("success", false);
            map.put("message", e.getMessage());
        }
        AjaxUtil.ajaxResponse(response, new JSONObject(map).toString(), AjaxUtil.RESPONCE_TYPE_JSON);
    }

    /**
     * 获取dpcoi系统用户
     * @param response 参数
     * @param dpcoiUserQuery 查询条件
     */
    @RequestMapping("getDpcoiUserList.do")
    public void getDpcoiUserList(HttpServletResponse response, DpcoiUserQuery dpcoiUserQuery){
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            List<Map<String, Object>> dpcoiUserList = this.dpcoiUserService.queryDpcoiUserList(dpcoiUserQuery);
            map.put("dpcoiUserList", dpcoiUserList);
            map.put("success", true);
        }catch (Exception e){
            e.printStackTrace();
            map.put("success", false);
            map.put("message", e.getMessage());
        }
        AjaxUtil.ajaxResponse(response, new JSONObject(map).toString(), AjaxUtil.RESPONCE_TYPE_JSON);
    }

    /**
     * 查询autocomplete自动下拉框中数据
     * @param response 参数
     * @param dpcoiUserQuery 查询条件
     */
    @RequestMapping("getAutocompleteDpcoiUserList.do")
    public void getAutocompleteDpcoiUserList(HttpServletResponse response, DpcoiUserQuery dpcoiUserQuery){
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            List<Map<String, Object>> dpcoiUserList = this.dpcoiUserService.queryAutocompleteDpcoiUserList(dpcoiUserQuery);
            map.put("dpcoiUserList", dpcoiUserList);
            map.put("success", true);
        }catch (Exception e){
            e.printStackTrace();
            map.put("success", false);
            map.put("message", e.getMessage());
        }
        AjaxUtil.ajaxResponse(response, new JSONObject(map).toString(), AjaxUtil.RESPONCE_TYPE_JSON);
    }

}
