package com.success.task.detail.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.success.task.detail.service.TaskOrderService;
import com.success.web.framework.util.AjaxUtil;
import com.success.web.framework.util.ServletAPIUtil;

@Controller
@RequestMapping("/taskOrder")
public class TaskOrderController {

	@Resource(name = "taskOrderService")
	private TaskOrderService taskOrderService;
	
	@RequestMapping("/editChangeTime.do")
	public void editChangeTime(HttpServletRequest request,HttpServletResponse response, Map<String, Object> model){
		Map<String, Object> map = new HashMap<String, Object>();
		try{
			Integer orderId =  ServletAPIUtil.getIntegerParameter("orderId", request);
			String order_1 = ServletAPIUtil.getStringParameter("order_1", request, "");
			String order_2 = ServletAPIUtil.getStringParameter("order_2", request, "");
			String order_3 = ServletAPIUtil.getStringParameter("order_3", request, "");
			String order_4 = ServletAPIUtil.getStringParameter("order_4", request, "");
			String order_5 = ServletAPIUtil.getStringParameter("order_5", request, "");
			String order_6 = ServletAPIUtil.getStringParameter("order_6", request, "");
			String order_7 = ServletAPIUtil.getStringParameter("order_7", request, "");
			String order_8 = ServletAPIUtil.getStringParameter("order_8", request, "");
			String order_9 = ServletAPIUtil.getStringParameter("order_9", request, "");
			String order_11 = ServletAPIUtil.getStringParameter("order_11", request, "");
			String order_8_input = ServletAPIUtil.getStringParameter("order_8_input", request, "");
			if(order_5.length() > 0){
				order_5 = order_5.substring(1);
			}
			if(order_6.length() > 0){
				order_6 = order_6.substring(1);
			}
			if(order_7.length() > 0){
				order_7 = order_7.substring(1);
			}
			if(order_8.length() > 0){
				order_8 = order_8.substring(1);
			}
			order_8 = order_8 + "<<?><?>>" + order_8_input;
			Map<String, Object> map2 = new HashMap<String, Object>();
			map2.put("orderId", orderId);
			map2.put("order_1", order_1);
			map2.put("order_2", order_2);
			map2.put("order_3", order_3);
			map2.put("order_4", order_4);
			map2.put("order_5", order_5);
			map2.put("order_6", order_6);
			map2.put("order_7", order_7);
			map2.put("order_8", order_8);
			map2.put("order_9", order_9);
			map2.put("order_11", order_11);
			this.taskOrderService.editTaskChangeTime(map2);
			map.put("success", true);
		}catch(Exception e){
			e.printStackTrace();
			map.put("success", false);
			map.put("message", e.getMessage());
		}
		AjaxUtil.ajaxResponse(response, new JSONObject(map).toString(), AjaxUtil.RESPONCE_TYPE_JSON);
	}
}
