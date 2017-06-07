package com.success.task.base.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.success.task.base.domain.TaskConfirmOrder;
import com.success.task.base.query.TaskConfirmOrderUserQuery;
import com.success.task.base.service.TaskConfirmOrderService;
import com.success.task.base.service.TaskConfirmOrderUserService;
import com.success.web.framework.exception.ServiceException;
import com.success.web.framework.mybatis.Page;
import com.success.web.framework.util.AjaxUtil;
import com.success.web.framework.util.JSONUtil;
import com.success.web.framework.util.ServletAPIUtil;

@Controller
@RequestMapping("/taskConfirmOrder")
public class TaskConfirmOrderController {

	@Resource(name = "taskConfirmOrderService")
	private TaskConfirmOrderService taskConfirmOrderService;
	@Resource(name = "taskConfirmOrderUserService")
	private TaskConfirmOrderUserService taskConfirmOrderUserService;
	
	@RequestMapping("/confirmTaskConfirmOrder.do")
	public void confirmTaskConfirmOrder(HttpServletRequest request,HttpServletResponse response, Map<String, Object> model){
		Map<String, Object> map = new HashMap<String, Object>();
		try{
			Integer confirmOrderId =  ServletAPIUtil.getIntegerParameter("confirmOrderId", request);
			this.taskConfirmOrderService.editConfirmTaskConfirmOrder(confirmOrderId);
			map.put("success", true);
		}catch(Exception e){
			e.printStackTrace();
			map.put("success", false);
			map.put("message", e.getMessage());
		}
		AjaxUtil.ajaxResponse(response, new JSONObject(map).toString(), AjaxUtil.RESPONCE_TYPE_JSON);
	}
	
	@RequestMapping("/editConfirmOrderUser.do")
	public void editConfirmOrderUser(HttpServletRequest request,HttpServletResponse response, Map<String, Object> model){
		Map<String, Object> map = new HashMap<String, Object>();
		try{
			Integer orderId =  ServletAPIUtil.getIntegerParameter("orderId", request);
			Integer userId =  ServletAPIUtil.getIntegerParameter("userId", request);
			String runCode = ServletAPIUtil.getStringParameter("runCode", request);
			this.taskConfirmOrderService.editConfirmOrderUser(orderId, userId, runCode);
			map.put("success", true);
		}catch(Exception e){
			e.printStackTrace();
			map.put("success", false);
			map.put("message", e.getMessage());
		}
		AjaxUtil.ajaxResponse(response, new JSONObject(map).toString(), AjaxUtil.RESPONCE_TYPE_JSON);
	}
	
	//查询确认人
	@RequestMapping("/queryPageTaskConfirmOrderUser.do")
	public void queryPageTaskConfirmOrderUser(HttpServletRequest request,
			HttpServletResponse response, Map<String, Object> model) {
		int start = ServletAPIUtil.getIntegerParameter("start", request, 0);
		int pageSize = ServletAPIUtil.getIntegerParameter("limit", request, 20);
		int pageIndex = 0;
		if(start == 0){
			pageIndex = 1;
		}else {
			pageIndex = start / pageSize + 1;
		}

		System.out.println("pageIndex:"+pageIndex+",pageSize:"+pageSize);
		try {
			TaskConfirmOrderUserQuery query = new TaskConfirmOrderUserQuery();
			query = this.taskConfirmOrderUserService.setTaskConfirmOrderUserQueryData(request);
			Page page = this.taskConfirmOrderUserService.getPageTaskConfirmOrderUser(query, pageIndex, pageSize);
			AjaxUtil.ajaxResponse(response, JSONUtil.getJSONObject(page)
					.toString(), AjaxUtil.RESPONCE_TYPE_TEXT);
	System.out.println("JSONUtil.getJSONObject(page).toString():"+JSONUtil.getJSONObject(page)
					.toString()+"");
		} catch (ServiceException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 修改确认人
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/editTaskConfirmOrder.do")
	public void updateTaskConfirmOrder(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		TaskConfirmOrder taskConfirmOrder = this.getTaskConfirmOrder(request);
		String jsonStr = "";
		System.out.println("修改TaskConfirmOrder>>>>>"+taskConfirmOrder.toString());
		System.out.println(".>>>>>>>>>>"+taskConfirmOrder.toString());

		try {
			Integer r_i = taskConfirmOrderUserService.editTaskConfirmOrderUser(taskConfirmOrder);
			if(!(taskConfirmOrder.getConfirmUserId()==null)){
				this.taskConfirmOrderService.editSendEmail(taskConfirmOrder);
			}
			if (r_i == 1) {
				jsonStr = "{'success':true,'message':'修改成功'}";
			}
		} catch (ServiceException e) {
			jsonStr = "{'success':false,'message':'修改失败'}";
			e.printStackTrace();
		}
		response.getWriter().write(jsonStr);
	}

	public TaskConfirmOrder getTaskConfirmOrder(HttpServletRequest request) {
		TaskConfirmOrder taskConfirmOrder = new TaskConfirmOrder();
		String confirmOrderId = request.getParameter("confirmOrderId");
		String confirmUserId = request.getParameter("confirmUserId");
		if (confirmOrderId != null && !confirmOrderId.equals("")) {
			taskConfirmOrder
					.setConfirmOrderId(Integer.parseInt(confirmOrderId));
		}
		if (confirmUserId != null && !confirmUserId.equals("")) {
			taskConfirmOrder.setConfirmUserId(Integer.parseInt(confirmUserId));
		}
		return taskConfirmOrder;
	}

}
