package com.success.sys.access.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.springframework.web.bind.annotation.RequestMapping;

import com.success.sys.access.query.AccessQuery;
import com.success.sys.access.service.AccessService;
import com.success.web.framework.exception.ServiceException;
import com.success.web.framework.mybatis.Page;
import com.success.web.framework.util.AjaxUtil;
import com.success.web.framework.util.JSONUtil;
import com.success.web.framework.util.ServletAPIUtil;

public class AccessController {

	@Resource(name = "accessService")
	private AccessService accessService;

	/**
	 * 获取权限列表
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("/queryPageAccess.do")
	public void queryPageAccess(HttpServletRequest request,HttpServletResponse response, Map<String, Object> model){
		int pageIndex = ServletAPIUtil.getIntegerParameter("page", request, 1);
		int pageSize = ServletAPIUtil.getIntegerParameter("rows", request, 20);
		try {
			AccessQuery query = new AccessQuery();
			query = this.accessService.setAccessQueryData(request);
			Page page = this.accessService.getPageAccess(query, pageIndex, pageSize);
			AjaxUtil.ajaxResponse(response, JSONUtil.getJSONObject(page).toString(), AjaxUtil.RESPONCE_TYPE_TEXT);
		} catch (ServiceException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
}
