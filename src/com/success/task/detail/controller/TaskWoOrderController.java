package com.success.task.detail.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import com.success.task.detail.service.TaskWoOrderService;
import com.success.web.framework.util.AjaxUtil;
import com.success.web.framework.util.ServletAPIUtil;

@Controller
@RequestMapping("/woOrder")
@Transactional
public class TaskWoOrderController {
	
	@Resource(name = "taskWoOrderService")
	private TaskWoOrderService taskWoOrderService;

	@RequestMapping("/refuseWoOrder.do")
	public void refuseWoOrder(HttpServletRequest request,HttpServletResponse response, Map<String, Object> model){
		Map<String, Object> map = new HashMap<String, Object>();
		try{
			Integer woOrderId =  ServletAPIUtil.getIntegerParameter("woOrderId", request);
			String refuseReason = ServletAPIUtil.getStringParameter("refuseReason", request, "");
			this.taskWoOrderService.editRefuseWoOrder(woOrderId, refuseReason);
			map.put("success", true);
			map.put("message", "成功！");
		}catch(Exception e){
			e.printStackTrace();
			map.put("success", false);
			map.put("message", e.getMessage());
		}
		AjaxUtil.ajaxResponse(response, new JSONObject(map).toString(), AjaxUtil.RESPONCE_TYPE_JSON);
	}
	
	@RequestMapping("/refuseWoOrder2.do")
	public void refuseWoOrder2(HttpServletRequest request,HttpServletResponse response, Map<String, Object> model){
		Map<String, Object> map = new HashMap<String, Object>();
		try{
			Integer woOrderId =  ServletAPIUtil.getIntegerParameter("woOrderId", request);
			String refuseReason = ServletAPIUtil.getStringParameter("refuseReason", request, "");
			this.taskWoOrderService.editRefuseWoOrder2(woOrderId, refuseReason);
			map.put("success", true);
			map.put("message", "成功！");
		}catch(Exception e){
			e.printStackTrace();
			map.put("success", false);
			map.put("message", e.getMessage());
		}
		AjaxUtil.ajaxResponse(response, new JSONObject(map).toString(), AjaxUtil.RESPONCE_TYPE_JSON);
	}
	
	@RequestMapping("/refuseWoOrder3.do")
	public void refuseWoOrder3(HttpServletRequest request,HttpServletResponse response, Map<String, Object> model){
		Map<String, Object> map = new HashMap<String, Object>();
		try{
			String woOrderIds =  ServletAPIUtil.getStringParameter("woOrderIds", request);
			String refuseReason = ServletAPIUtil.getStringParameter("refuseReason", request, "");
			this.taskWoOrderService.editRefuseWoOrder3(woOrderIds, refuseReason);
			map.put("success", true);
			map.put("message", "成功！");
		}catch(Exception e){
			e.printStackTrace();
			map.put("success", false);
			map.put("message", e.getMessage());
		}
		AjaxUtil.ajaxResponse(response, new JSONObject(map).toString(), AjaxUtil.RESPONCE_TYPE_JSON);
	}
	
	@RequestMapping("/acceptWoOrder.do")
	public void acceptWoOrder(HttpServletRequest request,HttpServletResponse response, Map<String, Object> model){
		Map<String, Object> map = new HashMap<String, Object>();
		try{
			Integer orderId =  ServletAPIUtil.getIntegerParameter("orderId", request);
			Integer woOrderId =  ServletAPIUtil.getIntegerParameter("woOrderId", request);
			this.taskWoOrderService.editAcceptWoOrder(woOrderId);
			this.taskWoOrderService.editCompleteWoOrder(orderId);
			map.put("success", true);
			map.put("message", "成功！");
		}catch(Exception e){
			e.printStackTrace();
			map.put("success", false);
			map.put("message", e.getMessage());
		}
		AjaxUtil.ajaxResponse(response, new JSONObject(map).toString(), AjaxUtil.RESPONCE_TYPE_JSON);
	}
	
	@RequestMapping("/refuseWoOrders.do")
	public void refuseWoOrders(HttpServletRequest request,HttpServletResponse response, Map<String, Object> model){
		Map<String, Object> map = new HashMap<String, Object>();
		try{
			String woOrderIds =  ServletAPIUtil.getStringParameter("woOrderIds", request, "");
			String refuseReason = ServletAPIUtil.getStringParameter("refuseReason", request, "");
			if(!"".equals(woOrderIds)){
				String[]  woOrderIdArray = woOrderIds.split(",");
				for(int i = 0; i < woOrderIdArray.length; i++){
					int woOrderId = Integer.parseInt(woOrderIdArray[i]);
					this.taskWoOrderService.editRefuseWoOrder(woOrderId, refuseReason);
				}
			}
			map.put("success", true);
			map.put("message", "成功！");
		}catch(Exception e){
			e.printStackTrace();
			map.put("success", false);
			map.put("message", e.getMessage());
		}
		AjaxUtil.ajaxResponse(response, new JSONObject(map).toString(), AjaxUtil.RESPONCE_TYPE_JSON);
	}
	
	@RequestMapping("/acceptWoOrders.do")
	public void acceptWoOrders(HttpServletRequest request,HttpServletResponse response, Map<String, Object> model){
		Map<String, Object> map = new HashMap<String, Object>();
		try{
			String woOrderIds =  ServletAPIUtil.getStringParameter("woOrderIds", request, "");
			Integer orderId = ServletAPIUtil.getIntegerParameter("orderId", request);
			if(!"".equals(woOrderIds)){
				String[]  woOrderIdArray = woOrderIds.split(",");
				for(int i = 0; i < woOrderIdArray.length; i++){
					int woOrderId = Integer.parseInt(woOrderIdArray[i]);
					this.taskWoOrderService.editAcceptWoOrder(woOrderId);
				}
			}
			this.taskWoOrderService.editCompleteWoOrder(orderId);
			map.put("success", true);
			map.put("message", "成功！");
		}catch(Exception e){
			e.printStackTrace();
			map.put("success", false);
			map.put("message", e.getMessage());
		}
		AjaxUtil.ajaxResponse(response, new JSONObject(map).toString(), AjaxUtil.RESPONCE_TYPE_JSON);
	}
}
