package com.success.sys.mainframe.controller;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dpcoi.woOrder.query.DpcoiWoOrderQuery;
import com.dpcoi.woOrder.query.RRProblemWoOrderQuery;
import com.dpcoi.woOrder.service.DpcoiWoOrderService;
import com.dpcoi.woOrder.service.RRProblemWoOrderService;
import com.success.task.detail.query.DetailQuery;
import com.success.task.detail.service.DetailService;
import com.success.web.framework.mybatis.Page;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.success.common.Constant;
import com.success.sys.menu.domain.UserMenu;
import com.success.sys.menu.query.UserMenuQuery;
import com.success.sys.menu.service.MenuService;
import com.success.sys.module.domain.Module;
import com.success.sys.module.service.ModuleService;
import com.success.sys.user.domain.User;
import com.success.sys.user.query.UserQuery;
import com.success.sys.user.service.UserService;
import com.success.sys.webservice.service.AsmxClient;
import com.success.sys.webservice.service.serviceImpl.AsmxClientImpl;
import com.success.web.framework.exception.ServiceException;
import com.success.web.framework.util.AjaxUtil;
import com.success.web.framework.util.ServletAPIUtil;

@Controller
public class MainframeController {
	
	@Resource(name = "moduleService")
	private ModuleService moduleService;
	
	@Resource(name = "menuService")
	private MenuService menuService;
	
	@Resource(name = "userService")
	private UserService userService;

	@Resource(name = "dpcoiWoOrderService")
	private DpcoiWoOrderService dpcoiWoOrderService;

	@Resource(name = "rRProblemWoOrderService")
	private RRProblemWoOrderService rRProblemWoOrderService;

	@Resource(name = "detailService")
	private DetailService detailService;
	
	@RequestMapping("/loginAuthenticateFromOA.do")
	public String loginAuthenticateFromOA(HttpServletRequest request, HttpServletResponse response, Map<String, Object> model){
		String userCode = ServletAPIUtil.getStringParameter("account_staffid", request);
		try {
			AsmxClient asmxClient = new AsmxClientImpl();
			String staffInfo = asmxClient.staffInfoService(userCode);
			if(staffInfo.length() > 20){
				UserQuery userQuery = new UserQuery();
				userQuery.setUserCode(userCode);
				User user = this.userService.queryUser(userQuery);
				if (user != null) {
					request.getSession().setAttribute(Constant.STAFF_KEY, user);
					
					model.put("staff", new JSONObject(user));
					model.put("userId", user.getUserId());
					model.put("userOrgId", user.getOrgId());
					model.put("userName", user.getUserName());
					
					List<Module> moduleList = this.moduleService.queryUserModuleList(user.getUserId());
					model.put("catalogList", moduleList);
					
					if(moduleList == null || moduleList.size() == 0){
						List<UserMenu> menuLis = new LinkedList<UserMenu>();
						model.put("subCatalogList", menuLis);
					}else {
						UserMenuQuery userMenuQuery = new UserMenuQuery();
						userMenuQuery.setUserId(user.getUserId());
						userMenuQuery.setModuleId(moduleList.get(0).getId());
						List<UserMenu> menuList = this.menuService.selectUserMenu(userMenuQuery);
						List<UserMenu> newMenuList = this.getNewUserMenu(user, menuList);
						model.put("subCatalogList", menuList);
					}
				}else {
					model.put("error", "用户不存在！");
					return "login/login";
				}
			}else {
				model.put("error", "系统警告：您没有权限登录！");
				return "login/login";
			}
		} catch (Exception e1) {
			e1.printStackTrace();
			return "login/login";
		}
		return "mainframe/mainframe";
	}

	@RequestMapping("/viewMainframe.do")
	public String viewMainframe(HttpServletRequest request,HttpServletResponse response, Map<String, Object> model) throws Exception{
		try{
			User user = (User)request.getSession().getAttribute(Constant.STAFF_KEY);
			model.put("staff", new JSONObject(user));
			model.put("userId", user.getUserId());
			model.put("userOrgId", user.getOrgId());
			model.put("userName", user.getUserName());
			
			List<Module> moduleList = this.moduleService.queryUserModuleList(user.getUserId());
			model.put("catalogList", moduleList);
			
			if(moduleList == null || moduleList.size() == 0){
				List<UserMenu> menuLis = new LinkedList<UserMenu>();
				model.put("subCatalogList", menuLis);
			}else {
				UserMenuQuery userMenuQuery = new UserMenuQuery();
				userMenuQuery.setUserId(user.getUserId());
				userMenuQuery.setModuleId(moduleList.get(0).getId());
				List<UserMenu> menuList = this.menuService.selectUserMenu(userMenuQuery);
				List<UserMenu> newMenuList = this.getNewUserMenu(user, menuList);
				model.put("subCatalogList", newMenuList);
			}

			return "mainframe/mainframe";
		}catch(ServiceException e){
			e.printStackTrace();
			return "error/errorPage";
		}
	}
	
	/**
	 * 获取菜单子菜单
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("/mainframe/getSubCatalog.do")
	public void getSubCatalog(HttpServletRequest request,HttpServletResponse response, Map<String, Object> model) throws Exception{
		Integer catalogId = ServletAPIUtil.getIntegerParameter("catalogId", request);
		UserMenuQuery userMenuQuery = new UserMenuQuery();
		User user = (User)request.getSession().getAttribute(Constant.STAFF_KEY);
		userMenuQuery.setUserId(user.getUserId());
		userMenuQuery.setModuleId(catalogId);
		List<UserMenu> menuList = this.menuService.selectUserMenu(userMenuQuery);
		List<UserMenu> newMenuList = this.getNewUserMenu(user, menuList);
		AjaxUtil.ajaxResponse(response, new JSONArray(newMenuList).toString() , AjaxUtil.RESPONCE_TYPE_JSON);
	}

	private List<UserMenu> getNewUserMenu(User user, List<UserMenu> menuList) throws Exception{
		List<UserMenu> newMenuList = new LinkedList<UserMenu>();
		for(UserMenu userMenu : menuList){
			Integer menuId = userMenu.getMenuId();
			Integer number = 0;
			if(menuId == 29){//RR代办
				RRProblemWoOrderQuery rrProblemWoOrderQuery = new RRProblemWoOrderQuery();
				rrProblemWoOrderQuery.setDpcoiUserId(user.getUserId());
				number = this.rRProblemWoOrderService.queryRRProblemWoOrderCount(rrProblemWoOrderQuery);
			}else if(menuId == 18){//DPCOI代办
				DpcoiWoOrderQuery dpcoiWoOrderQuery = new DpcoiWoOrderQuery();
				dpcoiWoOrderQuery.setDpcoiUserId(user.getUserId());
				number = this.dpcoiWoOrderService.queryDpcoiWoOrderCount(dpcoiWoOrderQuery);
			}else if(menuId == 1){//4M代办
				DetailQuery query = new DetailQuery();
				query.setUserId(user.getUserId());
				Page page = this.detailService.queryTaskAgentPage(query, 1, 10);
				number = Integer.valueOf(String.valueOf(page.getTotalNumber()));
			}
			userMenu.setWoOrderNumber(number);
			newMenuList.add(userMenu);
		}
		return newMenuList;
	}

}
