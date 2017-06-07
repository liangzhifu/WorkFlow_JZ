package com.success.task.screen.controller;

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

import com.success.sys.user.domain.User;
import com.success.task.base.domain.OrderContractState;
import com.success.task.contract.domain.TaskContractOrg;
import com.success.task.contract.query.TaskContractQuery;
import com.success.task.contract.service.TaskContractService;
import com.success.task.detail.domain.TaskOrder;
import com.success.task.detail.query.TaskOrderQuery;
import com.success.task.detail.service.TaskOrderService;
import com.success.task.screen.query.ScreenQuery;
import com.success.task.screen.service.TaskScreenService;
import com.success.web.framework.exception.ServiceException;
import com.success.web.framework.mybatis.Page;
import com.success.web.framework.util.AjaxUtil;
import com.success.web.framework.util.JSONUtil;
import com.success.web.framework.util.ServletAPIUtil;

@Controller
@RequestMapping("/taskScreen")
public class TaskScreenController {

	@Resource(name = "screenService")
	private TaskScreenService screenService;
	
	@Resource(name = "taskOrderService")
	private TaskOrderService taskOrderService;
	
	@Resource(name = "taskContractService")
	private TaskContractService taskContractService;
	
	@RequestMapping("/getOrderContractState.do")
	public void getOrderContractState(HttpServletRequest request,HttpServletResponse response, Map<String, Object> model){
		try{
			List<OrderContractState> orderContractStateList = this.screenService.queryOrderContractStates();
			AjaxUtil.ajaxResponse(response, new JSONArray(orderContractStateList).toString(), AjaxUtil.RESPONCE_TYPE_JSON);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/getOrderContractState2.do")
	public void getOrderContractState2(HttpServletRequest request,HttpServletResponse response, Map<String, Object> model){
		try{
			List<OrderContractState> orderContractStateList = this.screenService.queryOrderContractStates();
			List<OrderContractState> list2 = new LinkedList<OrderContractState>();
			for(int i = 0; i < orderContractStateList.size(); i++){
				OrderContractState OrderContractState = orderContractStateList.get(i);
				if("".equals(OrderContractState.getOrderContractStateCode())){
					
				}else {
					list2.add(orderContractStateList.get(i));
				}
			}
			AjaxUtil.ajaxResponse(response, new JSONArray(list2).toString(), AjaxUtil.RESPONCE_TYPE_JSON);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/getOrderContractUser.do")
	public void getOrderContractUser(HttpServletRequest request,HttpServletResponse response, Map<String, Object> model){
		try{
			List<User> userList = this.screenService.queryOrderContractUser();
			AjaxUtil.ajaxResponse(response, new JSONArray(userList).toString(), AjaxUtil.RESPONCE_TYPE_JSON);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/queryTaskScreenShowList.do")
	public void queryTaskScreenShowList(HttpServletRequest request,
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
			ScreenQuery query = this.screenService.setScreenQueryData(request);
			Page page = this.screenService.queryPage(pageIndex, pageSize, query);
			AjaxUtil.ajaxResponse(response, JSONUtil.getJSONObject(page)
					.toString(), AjaxUtil.RESPONCE_TYPE_TEXT);
		} catch (ServiceException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/getScreenEditDlg.do")
	public String getScreenEditDlg(HttpServletRequest request,HttpServletResponse response, Map<String, Object> model){
		Integer orderId =  ServletAPIUtil.getIntegerParameter("orderId", request);
		try {
			TaskOrderQuery taskOrderQuery = new TaskOrderQuery();
			taskOrderQuery.setOrderId(orderId);
			TaskOrder taskOrder = this.taskOrderService.selectTaskOrderByIdQuery(taskOrderQuery);
			TaskContractQuery taskContractQuery = new TaskContractQuery();
			taskContractQuery.setOrderId(orderId);
			List<TaskContractOrg> taskContractOrgList = this.taskContractService.queryTaskContractOrgs(taskContractQuery);
			String contractOrgs = "";
			if(taskContractOrgList != null){
				for(int i = 0; i < taskContractOrgList.size(); i++){
					contractOrgs = contractOrgs + "," + taskContractOrgList.get(i).getOrgId();
				}
			}
			if(!("".equals(contractOrgs))){
				contractOrgs = contractOrgs.substring(1);
			}
			model.put("orderId", orderId);
			model.put("orderContractState", taskOrder.getOrderContractState());
			model.put("contractOrgs", contractOrgs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "screen/screenEdit";
	}
	
	@RequestMapping("/editOrderContract.do")
	public void editOrderContract(HttpServletRequest request,HttpServletResponse response, Map<String, Object> model){
		Integer orderId =  ServletAPIUtil.getIntegerParameter("orderId", request);
		String contractState = ServletAPIUtil.getStringParameter("orderContractStateCode", request);
		String contractUser = ServletAPIUtil.getStringParameter("userId", request);
//		String[] contractOrgArray = request.getParameterValues("order_contract_check");
		Map<String, Object> map = new HashMap<String, Object>();
		try {
//			String orderTache = "";
//			if(contractOrgArray != null){
//				for(int i = 0; i < contractOrgArray.length; i++){
//					if("".equals(orderTache)){
//						
//					}else {
//						orderTache = orderTache + ",";
//					}
//					if("1".equals(contractOrgArray[i])){
//						orderTache = orderTache  + "制造科";
//					}else if("2".equals(contractOrgArray[i])){
//						orderTache = orderTache  + "生产技术";
//					}else if("3".equals(contractOrgArray[i])){
//						orderTache = orderTache  + "设备保全";
//					}else if("4".equals(contractOrgArray[i])){
//						orderTache = orderTache  + "工艺管理";
//					}else if("5".equals(contractOrgArray[i])){
//						orderTache = orderTache  + "计划";
//					}else if("6".equals(contractOrgArray[i])){
//						orderTache = orderTache  + "仓库";
//					}else if("7".equals(contractOrgArray[i])){
//						orderTache = orderTache  + "品质/客户品质";
//					}else if("8".equals(contractOrgArray[i])){
//						orderTache = orderTache  + "品质检查";
//					}else if("9".equals(contractOrgArray[i])){
//						orderTache = orderTache  + "过程评价";
//					}else if("10".equals(contractOrgArray[i])){
//						orderTache = orderTache  + "系统评价";
//					}else if("11".equals(contractOrgArray[i])){
//						orderTache = orderTache  + "IT/总务";
//					}
//				}
//			}
			TaskOrder taskOrder = new TaskOrder();
			taskOrder.setOrderId(orderId);
			taskOrder.setOrderContractState(contractState);
//			if("".equals(orderTache)){
//				orderTache = "无";
//			}
//			taskOrder.setOrderTache(orderTache);
			if(contractUser == null || "".equals(contractUser)){
				
			}else {
				taskOrder.setContractUser(Integer.valueOf(contractUser));
			}
			this.taskOrderService.updateTaskOrderContract(taskOrder);
//			TaskContractQuery taskContractQuery = new TaskContractQuery();
//			taskContractQuery.setOrderId(orderId);
//			this.taskContractService.deleteTaskContractOrg(taskContractQuery);
//			if(contractOrgArray != null){
//				for(int i = 0; i < contractOrgArray.length; i++){
//					TaskContractOrg taskContractOrg = new TaskContractOrg();
//					taskContractOrg.setOrderId(orderId);
//					taskContractOrg.setOrgId(Integer.valueOf(contractOrgArray[i]));
//					this.taskContractService.addTaskContractOrg(taskContractOrg);
//				}
//			}
			map.put("success", true);
		}catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("message", e.getMessage());
		}
		AjaxUtil.ajaxResponse(response, new JSONObject(map).toString(), AjaxUtil.RESPONCE_TYPE_JSON);
	}
	
}
