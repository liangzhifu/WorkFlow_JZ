package com.success.templet.tache.controller;

import java.io.IOException;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.success.sys.menu.domain.UserMenu;
import com.success.templet.tache.domain.TacheUser;
import com.success.templet.tache.query.TacheUserQuery;
import com.success.templet.tache.service.TacheUserService;
import com.success.web.framework.mybatis.Page;
import com.success.web.framework.util.AjaxUtil;
import com.success.web.framework.util.JSONUtil;
import com.success.web.framework.util.ServletAPIUtil;

@Controller
@RequestMapping("/tache")
public class TacheController {
	@Resource(name = "tacheUserService")
	private TacheUserService tacheUserService;

	@RequestMapping("/queryUserListJson.do")
	public void queryUserListJson(HttpServletRequest request,
			HttpServletResponse response, Map<String, Object> model) {
		System.out.println("查询人员信息");
		int start = ServletAPIUtil.getIntegerParameter("start", request, 0);
		int pageSize = ServletAPIUtil.getIntegerParameter("limit", request,
				150);
		try {
			int pageIndex = 0;
			if (start == 0) {
				pageIndex = 1;
			} else {
				pageIndex = start / pageSize + 1;
			}
			TacheUserQuery query = this.setTacheUserQuery(request);
			Page page = this.tacheUserService.getPageTacheUser(query,
					pageIndex, pageSize);
			AjaxUtil.ajaxResponse(response, JSONUtil.getJSONObject(page)
					.toString(), AjaxUtil.RESPONCE_TYPE_TEXT);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/queryTacheManagerUserList.do")
	public void queryTacheManagerUserList(HttpServletRequest request,
								  HttpServletResponse response, Map<String, Object> model) {
		System.out.println("查询人员信息");
		int start = ServletAPIUtil.getIntegerParameter("start", request, 0);
		int pageSize = ServletAPIUtil.getIntegerParameter("limit", request,
				150);
		try {
			int pageIndex = 0;
			if (start == 0) {
				pageIndex = 1;
			} else {
				pageIndex = start / pageSize + 1;
			}
			TacheUserQuery query = this.setTacheUserQuery(request);
			Page page = this.tacheUserService.queryTacheManagerUserList(query,
					pageIndex, pageSize);
			AjaxUtil.ajaxResponse(response, JSONUtil.getJSONObject(page)
					.toString(), AjaxUtil.RESPONCE_TYPE_TEXT);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/queryUserTacheListJson.do")
	public void queryTacheListJson(HttpServletRequest request,
			HttpServletResponse response, Map<String, Object> model) {
		System.out.println("查询人员权限信息" + request.toString());
		int start = ServletAPIUtil.getIntegerParameter("start", request, 0);
		int pageSize = ServletAPIUtil.getIntegerParameter("limit", request,
				150);
		try {
			int pageIndex = 0;
			if (start == 0) {
				pageIndex = 1;
			} else {
				pageIndex = start / pageSize + 1;
			}
			TacheUserQuery query = this.setTacheUserQuery(request);
			Page page = this.tacheUserService.getPageTache(query, pageIndex,
					pageSize);
			AjaxUtil.ajaxResponse(response, JSONUtil.getJSONObject(page)
					.toString(), AjaxUtil.RESPONCE_TYPE_TEXT);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	// 添加管理员
	@RequestMapping("/addManager.do")
	public void addManager(HttpServletRequest request,
							HttpServletResponse response, Map<String, Object> model) throws Exception{
		String userIdStr = request.getParameter("userIdStr");
		Integer tacheId = Integer.parseInt(request.getParameter("tacheId"));
		String jsonStr = "";
		try {
			String[] userIdArray = userIdStr.split(",");
			for(String userId : userIdArray){
				TacheUser tacheUser = new TacheUser();
				tacheUser.setUserId(Integer.valueOf(userId));
				tacheUser.setTacheId(tacheId);
				this.tacheUserService.addManager(tacheUser);
			}
			jsonStr = "{'success':true,'message':'修改成功'}";
		}catch (Exception e) {
			e.printStackTrace();
			jsonStr = "{'success':false,'message':'修改失败'}";
		}
		response.getWriter().write(jsonStr);
	}

	// 删除管理员
	@RequestMapping("/deleteManager.do")
	public void deleteManager(HttpServletRequest request,
						   HttpServletResponse response, Map<String, Object> model) throws Exception{
		String userIdStr = request.getParameter("userIdStr");
		Integer tacheId = Integer.parseInt(request.getParameter("tacheId"));
		String jsonStr = "";
		try {
			String[] userIdArray = userIdStr.split(",");
			for(String userId : userIdArray){
				TacheUser tacheUser = new TacheUser();
				tacheUser.setUserId(Integer.valueOf(userId));
				tacheUser.setTacheId(tacheId);
				this.tacheUserService.deleteManager(tacheUser);
			}
			jsonStr = "{'success':true,'message':'修改成功'}";
		}catch (Exception e) {
			e.printStackTrace();
			jsonStr = "{'success':false,'message':'修改失败'}";
		}
		response.getWriter().write(jsonStr);
	}

	// 修改管理员
	@RequestMapping("/editManager.do")
	public void editManager(HttpServletRequest request,
			HttpServletResponse response, Map<String, Object> model) {
		System.out.println("修改管理员信息>>>>>");
		Integer userId = Integer.parseInt(request.getParameter("userId"));
		String userName = request.getParameter("userName");
		Integer tacheId = Integer.parseInt(request.getParameter("tacheId"));
		String jsonStr = "";
		TacheUser tacheUser = new TacheUser();
		tacheUser.setUserId(userId);
		tacheUser.setUserName(userName);
		tacheUser.setTacheId(tacheId);
		tacheUserService.updateManager(tacheUser);
		jsonStr = "{'success':true,'message':'修改成功'}";
		try {
			response.getWriter().write(jsonStr);
		} catch (IOException e) {
			e.printStackTrace();
			jsonStr = "{'success':false,'message':'修改失败'}";
		}
	}

	@RequestMapping("/addTacheUser.do")
	public void addTacheUser(HttpServletRequest request,
			HttpServletResponse response, Map<String, Object> model) {
		System.out.println("增加人员权限信息>>>>>");
		String userIdArray = request.getParameter("userIdArray");
		Integer tacheId = Integer.parseInt(request.getParameter("tacheId"));
		String[] arrUserId = null;
		if (userIdArray != null && !userIdArray.equals("")) {
			arrUserId = userIdArray.split(",");
		}
		String jsonStr = "";
		if (arrUserId.length > 0) {
			for (int i = 0; i < arrUserId.length; i++) {
				TacheUser tacheUser = new TacheUser();
				tacheUser.setUserId(Integer.parseInt(arrUserId[i]));
				tacheUser.setTacheId(tacheId);
				tacheUserService.insertTacheUser(tacheUser);
			}
		}
		jsonStr = "{'success':true,'message':'增加成功'}";
		try {
			response.getWriter().write(jsonStr);
		} catch (IOException e) {
			e.printStackTrace();
			jsonStr = "{'success':false,'message':'增加失败'}";
		}
	}

	@RequestMapping("/delTacheUser.do")
	public void delTacheUser(HttpServletRequest request,
			HttpServletResponse response, Map<String, Object> model) {
		System.out.println("删除人员权限信息>>>>>");
		String userIdArray = request.getParameter("userIdArray");
		Integer tacheId = Integer.parseInt(request.getParameter("tacheId"));
		String[] arrUserId = null;
		if (userIdArray != null && !userIdArray.equals("")) {
			arrUserId = userIdArray.split(",");
		}
		String jsonStr = "";
		int r_i = 0;
		if (arrUserId.length > 0) {
			for (int i = 0; i < arrUserId.length; i++) {
				TacheUser tacheUser = new TacheUser();
				tacheUser.setUserId(Integer.parseInt(arrUserId[i]));
				tacheUser.setTacheId(tacheId);
				r_i += tacheUserService.deleteTacheUser(tacheUser);
			}
		}
		if (r_i == arrUserId.length) {
			jsonStr = "{'success':true,'message':'删除成功'}";
		} else {
			jsonStr = "{'success':false,'message':'删除失败'}";
		}
		try {
			response.getWriter().write(jsonStr);
		} catch (IOException e) {
			e.printStackTrace();
			jsonStr = "{'success':false,'message':'增加失败'}";
		}
	}

	// 查询条件
	public TacheUserQuery setTacheUserQuery(HttpServletRequest request) {
		TacheUserQuery query = new TacheUserQuery();
		Integer tacheId = null;
		if ((request.getParameter("tacheId") != null)
				&& (!request.getParameter("tacheId").equals(""))) {
			tacheId = Integer.parseInt(request.getParameter("tacheId"));
			query.setTacheId(tacheId);
		}
		if ((request.getParameter("tacheIdIsNot") != null)
				&& (!request.getParameter("tacheIdIsNot").equals(""))) {
			query.setTacheIdIsHave(request.getParameter("tacheIdIsNot"));
		}
		if ((request.getParameter("managerFlag") != null)
				&& (!request.getParameter("managerFlag").equals(""))) {
			query.setManagerFlag(request.getParameter("managerFlag"));
		}
		System.out.println("TacheUserQuery:" + query.toString());
		return query;
	}

}
