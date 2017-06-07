package com.success.task.detail.controller;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.success.common.Constant;
import com.success.common.IsDelay;
import com.success.common.ReturnInfo;
import com.success.sys.user.domain.User;
import com.success.task.base.domain.TaskConfirmOrder;
import com.success.task.base.domain.TaskOrderState;
import com.success.task.base.query.TaskConfirmOrderQuery;
import com.success.task.base.service.TaskConfirmOrderService;
import com.success.task.base.service.TaskOrderStateService;
import com.success.task.derived.service.ExportPDF;
import com.success.task.detail.domain.TaskOrder;
import com.success.task.detail.query.DetailQuery;
import com.success.task.detail.query.TaskOrderQuery;
import com.success.task.detail.service.DetailService;
import com.success.templet.task.domain.TaskType;
import com.success.templet.task.query.TaskTypeQuery;
import com.success.web.framework.exception.ServiceException;
import com.success.web.framework.mybatis.Page;
import com.success.web.framework.util.AjaxUtil;
import com.success.web.framework.util.JSONUtil;
import com.success.web.framework.util.ServletAPIUtil;

@Controller
@RequestMapping("/taskDetail")
public class DetailController {
	
	@Resource(name = "detailService")
	private DetailService detailService;
	
	@Resource(name = "exportPDF")
	private ExportPDF exportPDF;
	
	@Resource(name = "taskOrderStateService")
	private TaskOrderStateService taskOrderStateService;
	
	@Resource(name = "taskConfirmOrderService")
	private TaskConfirmOrderService taskConfirmOrderService;
	
	@RequestMapping("/doExportPDF.do")
	public void doExportPDF(HttpServletRequest request,HttpServletResponse response, Map<String, Object> model){
		Map<String, Object> map = new HashMap<String, Object>();
		try{
			Integer orderId =  ServletAPIUtil.getIntegerParameter("orderId", request);
			String path = request.getSession().getServletContext().getRealPath("/");
			//String fileName = this.exportPDF.exportPDF(orderId, path);
			String fileName = this.exportPDF.exportAllPDF(orderId, path);
			map.put("success", true);
			map.put("path", "/stdout/" + fileName);
		}catch(Exception e){
			e.printStackTrace();
			map.put("success", false);
			map.put("message", e.getMessage());
		}
		AjaxUtil.ajaxResponse(response, new JSONObject(map).toString(), AjaxUtil.RESPONCE_TYPE_JSON);
	}
	
	@RequestMapping("/getOrderState.do")
	public void getOrderState(HttpServletRequest request,HttpServletResponse response, Map<String, Object> model){
		try{
			List<TaskOrderState> taskOrderStateList = this.taskOrderStateService.queryTaskOrderStates();
			AjaxUtil.ajaxResponse(response, new JSONArray(taskOrderStateList).toString(), AjaxUtil.RESPONCE_TYPE_JSON);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/getIsDelay.do")
	public void getIsDelay(HttpServletRequest request,HttpServletResponse response, Map<String, Object> model){
		try{
			List<IsDelay> list = new LinkedList<IsDelay>();
			IsDelay isDelay = new IsDelay();
			isDelay.setIsDelayCode("");
			isDelay.setIsDelayName("全部");
			list.add(isDelay);
			isDelay = new IsDelay();
			isDelay.setIsDelayCode("1");
			isDelay.setIsDelayName("是");
			list.add(isDelay);
			isDelay = new IsDelay();
			isDelay.setIsDelayCode("0");
			isDelay.setIsDelayName("否");
			list.add(isDelay);
			AjaxUtil.ajaxResponse(response, new JSONArray(list).toString(), AjaxUtil.RESPONCE_TYPE_JSON);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/getTaskAddDlg.do")
	public String getTaskAddDlg(HttpServletRequest request,HttpServletResponse response, Map<String, Object> model){
		Integer taskTypeId =  ServletAPIUtil.getIntegerParameter("taskTypeId", request);
		try {
			User user = (User)request.getSession().getAttribute(Constant.STAFF_KEY);
			TaskTypeQuery query = new TaskTypeQuery();
			query.setTaskTypeId(taskTypeId);
			TaskType taskType = this.detailService.getTaskType(query);
			model.put("taskTypeJSON", new JSONObject(taskType));
			model.put("taskTypeId", taskTypeId);
			model.put("userId", user.getUserId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "task/taskAdd";
	}
	
	@RequestMapping("/getTaskEditDlg.do")
	public String getTaskEditDlg(HttpServletRequest request,HttpServletResponse response, Map<String, Object> model){
		Integer orderId =  ServletAPIUtil.getIntegerParameter("orderId", request);
		try {
			TaskOrderQuery query = new TaskOrderQuery();
			query.setOrderId(orderId);
			TaskOrder taskOrder = this.detailService.getTaskOrder(query);
			model.put("taskOrderJSON", new JSONObject(taskOrder));
			model.put("orderId", orderId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "task/taskEdit";
	}
	
	@RequestMapping("/getTaskDetailDlg.do")
	public String getTaskDetailDlg(HttpServletRequest request,HttpServletResponse response, Map<String, Object> model){
		Integer orderId =  ServletAPIUtil.getIntegerParameter("orderId", request);
		try {
			TaskOrderQuery query = new TaskOrderQuery();
			query.setOrderId(orderId);
			TaskOrder taskOrder = this.detailService.getTaskOrder(query);
			model.put("taskOrderJSON", new JSONObject(taskOrder));
			model.put("orderId", orderId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "task/taskDetail";
	}
	
	@RequestMapping("/getTaskStaffDetailDlg.do")
	public String getTaskStaffDetailDlg(HttpServletRequest request,HttpServletResponse response, Map<String, Object> model){
		Integer orderId =  ServletAPIUtil.getIntegerParameter("orderId", request);
		try {
			User user = (User)request.getSession().getAttribute(Constant.STAFF_KEY);
			Integer userId = user.getUserId();
			TaskOrderQuery query = new TaskOrderQuery();
			query.setOrderId(orderId);
			TaskOrder taskOrder = this.detailService.getStaffTaskOrder(query, userId);
			model.put("taskOrderJSON", new JSONObject(taskOrder));
			model.put("orderId", orderId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "task/taskWoOrderConfirm";
	}
	
	@RequestMapping("/getTaskConfirmDlg.do")
	public String getTaskConfirmDlg(HttpServletRequest request,HttpServletResponse response, Map<String, Object> model){
		Integer orderId =  ServletAPIUtil.getIntegerParameter("orderId", request);
		Integer confirmOrderId =  ServletAPIUtil.getIntegerParameter("confirmOrderId", request);
		try {
			TaskConfirmOrderQuery taskConfirmOrderQuery = new TaskConfirmOrderQuery();
			taskConfirmOrderQuery.setConfirmOrderId(confirmOrderId);
			TaskConfirmOrder taskConfirmOrder = this.taskConfirmOrderService.selectTaskConfirmOrderByIdQuery(taskConfirmOrderQuery);
			TaskOrderQuery query = new TaskOrderQuery();
			query.setOrderId(orderId);
			TaskOrder taskOrder = this.detailService.getTaskOrder(query);
			model.put("taskOrderJSON", new JSONObject(taskOrder));
			model.put("orderId", orderId);
			model.put("confirmOrderId", confirmOrderId);
			model.put("runCode", taskConfirmOrder.getRunCode());
			TaskConfirmOrderQuery taskConfirmOrderQuery2 = new TaskConfirmOrderQuery();
			taskConfirmOrderQuery2.setOrderId(orderId);
			List<TaskConfirmOrder> taskConfirmOrderList = this.taskConfirmOrderService.queryTaskConfirmOrders(taskConfirmOrderQuery2);
			String confirm_confirm_user = "";
			String quality2_confirm_user = "";
			for(int i = 0; i < taskConfirmOrderList.size(); i++){
				TaskConfirmOrder taskConfirmOrder2 = taskConfirmOrderList.get(i);
				String runCode = taskConfirmOrder2.getRunCode();
				if("confirm_confirm".equals(runCode)){
					confirm_confirm_user = taskConfirmOrder2.getConfirmUserName();
				}else if("quality2_confirm".equals(runCode)){
					quality2_confirm_user = taskConfirmOrder2.getConfirmUserName();
				}
			}
			model.put("confirm_confirm_user", confirm_confirm_user);
			model.put("quality2_confirm_user", quality2_confirm_user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "task/taskConfirm";
	}
	
	@RequestMapping("/getTaskRefuseDlg.do")
	public String getTaskRefuseDlg(HttpServletRequest request,HttpServletResponse response, Map<String, Object> model){
		Integer orderId =  ServletAPIUtil.getIntegerParameter("orderId", request);
		try {
			TaskOrderQuery query = new TaskOrderQuery();
			query.setOrderId(orderId);
			TaskOrder taskOrder = this.detailService.getTaskOrder(query);
			model.put("taskOrderJSON", new JSONObject(taskOrder));
			model.put("orderId", orderId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "task/taskRefuse";
	}
	
	/**
	 * 添加任务
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/addTask.do")
	public void addTask(HttpServletRequest request,HttpServletResponse response, Map<String, Object> model){
		Map<String, Object> map = new HashMap<String, Object>();
		try{
			Integer taskTypeId =  ServletAPIUtil.getIntegerParameter("taskTypeId", request);
			this.detailService.addTask(request, taskTypeId);
			map.put("success", true);
		}catch(Exception e){
			e.printStackTrace();
			map.put("success", false);
			map.put("message", e.getMessage());
		}
		AjaxUtil.ajaxResponse(response, new JSONObject(map).toString(), AjaxUtil.RESPONCE_TYPE_JSON);
	}
	
	@RequestMapping("/delTask.do")
	public void delTask(HttpServletRequest request,HttpServletResponse response, Map<String, Object> model){
		Map<String, Object> map = new HashMap<String, Object>();
		try{
			User user = (User)request.getSession().getAttribute(Constant.STAFF_KEY);
			Integer orderId =  ServletAPIUtil.getIntegerParameter("orderId", request);
			String invalidateText = ServletAPIUtil.getStringParameter("invalidateText", request);
			ReturnInfo returnInfo = this.detailService.deleteTask(orderId, invalidateText, user);
			map.put("success", returnInfo.getReturnFlag());
			map.put("message", returnInfo.getReturnMessage());	
		}catch(Exception e){
			e.printStackTrace();
			map.put("success", false);
			map.put("message", e.getMessage());
		}
		AjaxUtil.ajaxResponse(response, new JSONObject(map).toString(), AjaxUtil.RESPONCE_TYPE_JSON);
	}
	
	@RequestMapping("/doExportExcel.do")
	public void doDown(HttpServletRequest request,HttpServletResponse response, Map<String, Object> model){
		Map<String, Object> map = new HashMap<String, Object>();
		try{
			String path = request.getSession().getServletContext().getRealPath("/");
        	DetailQuery query = this.detailService.setDetailQueryData(request);
        	String fileName = this.detailService.doExportExcle(query, path);
			map.put("success", true);
			map.put("path", "/stdout/" + fileName);
		}catch(Exception e){
			e.printStackTrace();
			map.put("success", false);
			map.put("message", e.getMessage());
		}
		AjaxUtil.ajaxResponse(response, new JSONObject(map).toString(), AjaxUtil.RESPONCE_TYPE_JSON);
	}
	
	@RequestMapping("/updateWoOrder.do")
	public void updateWoOrder(HttpServletRequest request,HttpServletResponse response, Map<String, Object> model){
		Map<String, Object> map = new HashMap<String, Object>();
		try{
			Integer orderId =  ServletAPIUtil.getIntegerParameter("orderId", request);
			User user = (User)request.getSession().getAttribute(Constant.STAFF_KEY);
			Integer userId = user.getUserId();
			this.detailService.editWoOrder(request, orderId, userId);
			map.put("success", true);
		}catch(Exception e){
			e.printStackTrace();
			map.put("success", false);
			map.put("message", e.getMessage());
		}
		AjaxUtil.ajaxResponse(response, new JSONObject(map).toString(), AjaxUtil.RESPONCE_TYPE_JSON);
	}
	
	@RequestMapping("/queryPageTask.do")
	public void queryPageTask(HttpServletRequest request,
			HttpServletResponse response, Map<String, Object> model) {
		int pageIndex = ServletAPIUtil.getIntegerParameter("page", request, 1);
		int pageSize = ServletAPIUtil.getIntegerParameter("rows", request, 20);
		try {
			DetailQuery query = new DetailQuery();
			query = this.detailService.setDetailQueryData(request);
			Page page = this.detailService.queryDetailPage(query, pageIndex, pageSize);
			AjaxUtil.ajaxResponse(response, JSONUtil.getJSONObject(page)
					.toString(), AjaxUtil.RESPONCE_TYPE_TEXT);
		} catch (ServiceException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		} 
	}
	
	@RequestMapping("/queryTaskListJson.do")
	public void queryTaskListJson(HttpServletRequest request,
			HttpServletResponse response, Map<String, Object> model) {
		int start = ServletAPIUtil.getIntegerParameter("start", request, 0);
		int pageSize = ServletAPIUtil.getIntegerParameter("limit", request, 20);
		try {
			int pageIndex = 0;
			if(start == 0){
				pageIndex = 1;
			}else {
				pageIndex = start / pageSize + 1;
			}
			DetailQuery query = this.detailService.setDetailQueryData(request);
			//User user = (User)request.getSession().getAttribute(Constant.STAFF_KEY);
			//query.setIsPermission(1);
			//List<Integer> permissionUsers = this.detailService.queryPermisssionUsers(user.getUserId());
			//query.setPermissionUsers(permissionUsers);
			Page page = this.detailService.queryDetailPage(query, pageIndex, pageSize);
			AjaxUtil.ajaxResponse(response, JSONUtil.getJSONObject(page)
					.toString(), AjaxUtil.RESPONCE_TYPE_TEXT);
		} catch (ServiceException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/queryTaskListJson2.do")
	public void queryTaskListJson2(HttpServletRequest request,
			HttpServletResponse response, Map<String, Object> model) {
		int start = ServletAPIUtil.getIntegerParameter("start", request, 0);
		int pageSize = ServletAPIUtil.getIntegerParameter("limit", request, 20);
		try {
			int pageIndex = 0;
			if(start == 0){
				pageIndex = 1;
			}else {
				pageIndex = start / pageSize + 1;
			}
			DetailQuery query = this.detailService.setDetailQueryData(request);
			//User user = (User)request.getSession().getAttribute(Constant.STAFF_KEY);
			//query.setIsPermission(1);
			//List<Integer> permissionUsers = this.detailService.queryPermisssionUsers(user.getUserId());
			//query.setPermissionUsers(permissionUsers);
			Page page = this.detailService.queryDetailPage2(query, pageIndex, pageSize);
			AjaxUtil.ajaxResponse(response, JSONUtil.getJSONObject(page)
					.toString(), AjaxUtil.RESPONCE_TYPE_TEXT);
		} catch (ServiceException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/queryTaskAgentListJson.do")
	public void queryTaskAgentListJson(HttpServletRequest request,
			HttpServletResponse response, Map<String, Object> model) {
		int start = ServletAPIUtil.getIntegerParameter("start", request, 0);
		int pageSize = ServletAPIUtil.getIntegerParameter("limit", request, 20);
		try {
			int pageIndex = 0;
			if(start == 0){
				pageIndex = 1;
			}else {
				pageIndex = start / pageSize + 1;
			}
			DetailQuery query = this.detailService.setDetailQueryData(request);
			User user = (User)request.getSession().getAttribute(Constant.STAFF_KEY);
			query.setUserId(user.getUserId());
			Page page = this.detailService.queryTaskAgentPage(query, pageIndex, pageSize);
			AjaxUtil.ajaxResponse(response, JSONUtil.getJSONObject(page)
					.toString(), AjaxUtil.RESPONCE_TYPE_TEXT);
		} catch (ServiceException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/taskCycleEdit.do")
	public void taskCycleEdit(HttpServletRequest request,HttpServletResponse response, Map<String, Object> model){
		Map<String, Object> map = new HashMap<String, Object>();
		try{
			Integer orderId = Integer.parseInt(request.getParameter("orderId"));
			Integer cycleId = Integer.parseInt(request.getParameter("cycleId"));
			this.detailService.editTaskNoticeCycle(request, orderId, cycleId);
			map.put("success", true);
			map.put("message", "通知周期修改成功！");
		}catch(Exception e){
			e.printStackTrace();
			map.put("success", false);
			map.put("message", e.getMessage());
		}
		AjaxUtil.ajaxResponse(response, new JSONObject(map).toString(), AjaxUtil.RESPONCE_TYPE_JSON);
	}
}
