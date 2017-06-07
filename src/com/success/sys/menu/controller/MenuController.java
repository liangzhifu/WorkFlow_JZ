package com.success.sys.menu.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.success.sys.menu.domain.UserMenu;
import com.success.sys.menu.query.UserMenuQuery;
import com.success.sys.menu.service.MenuService;
import com.success.web.framework.exception.DaoException;
import com.success.web.framework.exception.ServiceException;
import com.success.web.framework.util.AjaxUtil;
import com.success.web.framework.util.JSONUtil;

@Controller
@RequestMapping("/menu")
public class MenuController {

	@Resource(name = "menuService")
	private MenuService menuService;

	@RequestMapping("/queryUserMenu.do")
	public void queryUserMenu(HttpServletRequest request,
			HttpServletResponse response, Map<String, Object> model) {
		System.out.println("查询菜单权限>>>>>>>");
		try {
			UserMenuQuery query = menuService.getUserMenuQuery(request);
			System.out.println("UserMenuQuery:" + query.toString());
			List<UserMenu> userMenuList = menuService.selectUserMenu(query);
			System.out.println(userMenuList.toString());
			AjaxUtil.ajaxResponse(response, JSONUtil
					.getJSONObject(userMenuList).toString(),
					AjaxUtil.RESPONCE_TYPE_TEXT);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	@RequestMapping("/queryMenuByUserId.do")
	public void queryMenuByUserId(HttpServletRequest request,
			HttpServletResponse response, Map<String, Object> model) {
		System.out.println("查询菜单权限>>>>>>>");
		try {
			UserMenuQuery query = menuService.getUserMenuQuery(request);
			System.out.println("UserMenuQuery:" + query.toString());
			List<UserMenu> userMenuList = menuService.selectMenuByUserId(query);
			System.out.println(userMenuList.toString());
			AjaxUtil.ajaxResponse(response, JSONUtil
					.getJSONObject(userMenuList).toString(),
					AjaxUtil.RESPONCE_TYPE_TEXT);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}


	@RequestMapping("/addUserMenu.do")
	public void addUsermenu(HttpServletRequest request,
			HttpServletResponse response, Map<String, Object> model) {
		System.out.println("增加菜单权限>>>>>>>");
		String menuIdArray = request.getParameter("menuIdArray");
		Integer userId = Integer.parseInt(request.getParameter("userId"));
		String[] arrMenuId = null;
		if (menuIdArray != null && !menuIdArray.equals("")) {
			arrMenuId = menuIdArray.split(",");
		}
		String jsonStr = "";
		if (arrMenuId.length > 0) {

			for (int i = 0; i < arrMenuId.length; i++) {
				UserMenu userMenu = new UserMenu();
				userMenu.setMenuId(Integer.parseInt(arrMenuId[i]));
				userMenu.setUserId(userId);
				menuService.insertUserMenu(userMenu);
			}
		}
		jsonStr = "{'success':true,'message':'增加成功'}";
		try {
			response.getWriter().write(jsonStr);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@RequestMapping("/delUserMenu.do")
	public void delUsermenu(HttpServletRequest request,
			HttpServletResponse response, Map<String, Object> model) {
		System.out.println("删除菜单权限>>>>>>>");
		String menuIdArray = request.getParameter("menuIdArray");
		Integer userId = Integer.parseInt(request.getParameter("userId"));
		String[] arrMenuId = null;
		if (menuIdArray != null && !menuIdArray.equals("")) {
			arrMenuId = menuIdArray.split(",");
		}
		String jsonStr = "";
		int r_i=0;
		if (arrMenuId.length > 0) {

			for (int i = 0; i < arrMenuId.length; i++) {
				UserMenu userMenu = new UserMenu();
				userMenu.setMenuId(Integer.parseInt(arrMenuId[i]));
				userMenu.setUserId(userId);
				r_i += menuService.deleteUserMenu(userMenu);
			}
		}
		if(r_i==arrMenuId.length){
			jsonStr = "{'success':true,'message':'删除成功'}";
		}else{
			jsonStr = "{'success':false,'message':'删除失败'}";
		}
			
		try {
			response.getWriter().write(jsonStr);
		} catch (IOException e) {
			e.printStackTrace();
			jsonStr = "{'success':false,'message':'删除失败'}";
		}
	}
}