package com.success.history.base.controller;

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

import com.success.history.base.domain.HistoryAgreement;
import com.success.history.base.query.HistoryAgreementQuery;
import com.success.history.base.service.HistoryService;
import com.success.sys.user.domain.User;
import com.success.web.framework.exception.ServiceException;
import com.success.web.framework.mybatis.Page;
import com.success.web.framework.util.AjaxUtil;
import com.success.web.framework.util.JSONUtil;
import com.success.web.framework.util.ServletAPIUtil;

@Controller
@RequestMapping("history")
public class HistoryController {

	@Resource(name = "historyService")
	private HistoryService historyService;
	
	@RequestMapping("/getAgreementUser.do")
	public void getAgreementUser(HttpServletRequest request,HttpServletResponse response, Map<String, Object> model){
		try{
			List<User> userList = this.historyService.queryAgreementEditUser();
			AjaxUtil.ajaxResponse(response, new JSONArray(userList).toString(), AjaxUtil.RESPONCE_TYPE_JSON);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/queryHistoryAgreementListJson.do")
	public void queryHistoryAgreementListJson(HttpServletRequest request,
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
			HistoryAgreementQuery query = this.historyService.setHistoryAgreementQueryData(request);
			Page page = this.historyService.queryHistoryAgreementPage(query, pageIndex, pageSize);
			AjaxUtil.ajaxResponse(response, JSONUtil.getJSONObject(page)
					.toString(), AjaxUtil.RESPONCE_TYPE_TEXT);
		} catch (ServiceException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/getHistoryAgreementDetailDlg.do")
	public String getHistoryAgreementDetailDlg(HttpServletRequest request,HttpServletResponse response, Map<String, Object> model){
		Integer historyAgreementId =  ServletAPIUtil.getIntegerParameter("historyAgreementId", request);
		try {
			HistoryAgreement historyAgreement = this.historyService.queryHistoryAgreementInfo(historyAgreementId);
			model.put("historyAgreementJSON", new JSONObject(historyAgreement));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "history/agreementDetail";
	}
	
}
