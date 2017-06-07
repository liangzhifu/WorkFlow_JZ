package com.success.task.statistics.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.success.task.detail.domain.Detail;
import com.success.task.statistics.query.StatisticsOrderQuery;
import com.success.task.statistics.service.StatisticsOrderService;
import com.success.web.framework.util.AjaxUtil;
import com.success.web.framework.util.JSONUtil;

@Controller
@RequestMapping("/statisticsOrder")
public class StatisticsOrderController {

	@Resource(name = "statisticsOrderService")
	private StatisticsOrderService statisticsOrderService;
	//任务统计查询工单
	@RequestMapping("/queryStatisticsOrderJson.do")
	public void queryStatisticsOrderJson(HttpServletRequest request,
			HttpServletResponse response, Map<String, Object> model) {
		try {
			StatisticsOrderQuery query = this.statisticsOrderService.setStatisticsOrderQueryData(request);
			Integer tacheId = query.getTacheId();
			List<Detail> detailList = null;
			if(tacheId == null){
				Integer isComplete = query.getIsComplete();
				if(isComplete == null){
					detailList = this.statisticsOrderService.queryDetailForAll(query);
				}else if(isComplete.equals(2)){
					detailList = this.statisticsOrderService.queryDetailForNoAgreement(query);
				}else if(isComplete.equals(3)){
					detailList = this.statisticsOrderService.queryDetailForImprove(query);
				}else if(isComplete.equals(4)){
					detailList = this.statisticsOrderService.queryDetailForNG(query);
				}else {
					detailList = this.statisticsOrderService.queryDetailForAll(query);
				}
			}else {
				detailList = this.statisticsOrderService.queryDetailForTache(query);
			}
			AjaxUtil.ajaxResponse(response, JSONUtil
					.getJSONObject(detailList).toString(),
					AjaxUtil.RESPONCE_TYPE_TEXT);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
