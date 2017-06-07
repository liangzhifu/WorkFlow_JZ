package com.success.task.screen.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.success.task.screen.domain.TaskScreen;
import com.success.task.screen.query.ScreenQuery;
import com.success.task.screen.service.TaskScreenShowService;
import com.success.web.framework.exception.ServiceException;
import com.success.web.framework.mybatis.Page;
import com.success.web.framework.util.AjaxUtil;
import com.success.web.framework.util.JSONUtil;
import com.success.web.framework.util.ServletAPIUtil;

@Controller
@RequestMapping("/taskScreenShow")
public class TaskScreenShowController {

	@Resource(name = "screenShowService")
	private TaskScreenShowService screenShowService;
	
	@RequestMapping("/queryTaskScreenShowList.do")
	public void queryTaskListJson2(HttpServletRequest request,
			HttpServletResponse response, Map<String, Object> model) {
		int pageIndex = ServletAPIUtil.getIntegerParameter("page", request, 0);
		int pageSize = ServletAPIUtil.getIntegerParameter("pageSize", request, 20);
		try {
			Long pageCount = this.screenShowService.queryPageCount();
			int dataPage = 1;
			if(pageCount % pageSize == 0){
				dataPage = Integer.parseInt(String.valueOf(pageCount / pageSize));
			}else {
				dataPage = Integer.parseInt(String.valueOf(pageCount / pageSize)) + 1;
			}
			if(pageIndex > dataPage) pageIndex = pageIndex % dataPage;
			if(pageIndex == 0) pageIndex = dataPage;
			Page page = this.screenShowService.queryPage(pageIndex, pageSize);
			AjaxUtil.ajaxResponse(response, JSONUtil.getJSONObject(page)
					.toString(), AjaxUtil.RESPONCE_TYPE_TEXT);
		} catch (ServiceException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/queryTaskScreenCount.do")
	public void queryTaskScreenCount(HttpServletRequest request,
			HttpServletResponse response, Map<String, Object> model) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			Long pageCount = this.screenShowService.queryOverTimeCount();
			map.put("success", true);
			map.put("pageCount", pageCount);
		} catch (ServiceException e) {
			map.put("success", false);
			e.printStackTrace();
		} 
		AjaxUtil.ajaxResponse(response, new JSONObject(map).toString(), AjaxUtil.RESPONCE_TYPE_JSON);
	}
	
	@RequestMapping("/queryTaskScreenShowJson.do")
	public void queryTaskScreenShowJson(HttpServletRequest request,
			HttpServletResponse response, Map<String, Object> model) {
		try {
			ScreenQuery query = this.screenShowService.setScreenQueryData(request);
			List<TaskScreen> taskScreenList = this.screenShowService.getTaskScreenList(query);
			AjaxUtil.ajaxResponse(response, JSONUtil
					.getJSONObject(taskScreenList).toString(),
					AjaxUtil.RESPONCE_TYPE_TEXT);
		} catch (ServiceException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/doExportExcel.do")
	public void doDown(HttpServletRequest request,HttpServletResponse response, Map<String, Object> model){
		Map<String, Object> map = new HashMap<String, Object>();
		try{
			String path = request.getSession().getServletContext().getRealPath("/");
			ScreenQuery query = this.screenShowService.setScreenQueryData(request);
        	String fileName = this.screenShowService.doExportExcle(query, path);
			map.put("success", true);
			map.put("path", "/stdout/" + fileName);
		}catch(Exception e){
			e.printStackTrace();
			map.put("success", false);
			map.put("message", e.getMessage());
		}
		AjaxUtil.ajaxResponse(response, new JSONObject(map).toString(), AjaxUtil.RESPONCE_TYPE_JSON);
	}
	
}
