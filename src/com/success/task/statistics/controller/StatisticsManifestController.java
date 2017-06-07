package com.success.task.statistics.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.success.task.detail.domain.Detail;
import com.success.task.statistics.service.StatisticsManifestService;
import com.success.web.framework.exception.ServiceException;
import com.success.web.framework.util.AjaxUtil;
import com.success.web.framework.util.JSONUtil;
import com.success.web.framework.util.ServletAPIUtil;

@Controller
@RequestMapping("/statisticsManifest")
public class StatisticsManifestController {

	@Resource(name = "statisticsManifestService")
	private StatisticsManifestService statisticsManifestService;
	
	@RequestMapping("/queryStatisticsManifestCount.do")
	public void queryStatisticsManifestCount(HttpServletRequest request,
			HttpServletResponse response, Map<String, Object> model) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String param = ServletAPIUtil.getStringParameter("param", request);
			Long pageCount = this.statisticsManifestService.queryManifestCount(param);
			map.put("success", true);
			map.put("count", pageCount);
		} catch (ServiceException e) {
			map.put("success", false);
			e.printStackTrace();
		} 
		AjaxUtil.ajaxResponse(response, new JSONObject(map).toString(), AjaxUtil.RESPONCE_TYPE_JSON);
	}
	
	@RequestMapping("/queryManifestOrderJson.do")
	public void queryManifestOrderJson(HttpServletRequest request,
			HttpServletResponse response, Map<String, Object> model) {
		try {
			String param = ServletAPIUtil.getStringParameter("param", request);
			List<Detail> detailList = this.statisticsManifestService.queryDetailForAll(param);

			AjaxUtil.ajaxResponse(response, JSONUtil
					.getJSONObject(detailList).toString(),
					AjaxUtil.RESPONCE_TYPE_TEXT);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
}
