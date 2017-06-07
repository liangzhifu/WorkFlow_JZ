package com.success.task.base.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.success.task.base.domain.TaskNoticeCycle;
import com.success.task.base.query.TaskNoticeCycleQuery;
import com.success.task.base.service.TaskNoticeCycleService;
import com.success.web.framework.util.ServletAPIUtil;

@Controller
@RequestMapping("/cycle")
public class TaskNoticeCycleController {
	
	@Resource(name = "taskNoticeCycleService")
	private TaskNoticeCycleService taskNoticeCycleService;
	
	@RequestMapping("/getCycleEditDlg.do")
	public String getCycleEditDlg(HttpServletRequest request,HttpServletResponse response, Map<String, Object> model){
		Integer orderId =  ServletAPIUtil.getIntegerParameter("orderId", request);
		Integer cycleId =  ServletAPIUtil.getIntegerParameter("cycleId", request);
		try {
			TaskNoticeCycleQuery query = new TaskNoticeCycleQuery();
			query.setCycleId(cycleId);
			TaskNoticeCycle taskNoticeCycle = this.taskNoticeCycleService.selectTaskNoticeCycleByIdQuery(query);
			model.put("taskNoticeCycleJSON", new JSONObject(taskNoticeCycle));
			model.put("orderId", orderId);
			model.put("cycleId", cycleId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "task/taskCycleEdit";
	}
}
