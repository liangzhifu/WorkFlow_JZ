package com.success.agreement.base.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.success.templet.tache.domain.TacheUser;
import com.success.templet.tache.domain.TaskTache;
import com.success.templet.tache.query.TacheUserQuery;
import com.success.templet.tache.query.TaskTacheQuery;
import com.success.templet.tache.service.TacheUserService;
import com.success.templet.tache.service.TaskTacheService;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.success.agreement.base.domain.Agreement;
import com.success.agreement.base.domain.AgreementState;
import com.success.agreement.base.query.AgreementQuery;
import com.success.agreement.base.service.AgreementService;
import com.success.common.Constant;
import com.success.history.base.service.HistoryService;
import com.success.sys.user.domain.User;
import com.success.task.derived.service.ExportPDF;
import com.success.web.framework.exception.ServiceException;
import com.success.web.framework.mybatis.Page;
import com.success.web.framework.util.AjaxUtil;
import com.success.web.framework.util.JSONUtil;
import com.success.web.framework.util.ServletAPIUtil;

@Controller
@RequestMapping("agreement")
public class AgreementController {

	@Resource(name = "agreementService")
	private AgreementService agreementService;
	
	@Resource(name = "historyService")
	private HistoryService historyService;

	@Resource(name = "taskTacheService")
	private TaskTacheService taskTacheService;

	@Resource(name = "tacheUserService")
	private TacheUserService tacheUserService;
	
	@Resource(name = "exportPDF")
	private ExportPDF exportPDF;
	
	@RequestMapping("/doExportPDF.do")
	public void doExportPDF(HttpServletRequest request,HttpServletResponse response, Map<String, Object> model){
		Map<String, Object> map = new HashMap<String, Object>();
		try{
			Integer orderId =  ServletAPIUtil.getIntegerParameter("orderId", request);
			Integer agreementId =  ServletAPIUtil.getIntegerParameter("agreementId", request);
			String path = request.getSession().getServletContext().getRealPath("/");
			String fileName = this.exportPDF.exportPDF2(orderId, agreementId, path);
			map.put("success", true);
			map.put("path", "/stdout/" + fileName);
		}catch(Exception e){
			e.printStackTrace();
			map.put("success", false);
			map.put("message", e.getMessage());
		}
		AjaxUtil.ajaxResponse(response, new JSONObject(map).toString(), AjaxUtil.RESPONCE_TYPE_JSON);
	}
	
	@RequestMapping("/getAgreementAddDlg.do")
	public String getAgreementAddDlg(HttpServletRequest request,
			HttpServletResponse response, Map<String, Object> model) {
		Integer orderId =  ServletAPIUtil.getIntegerParameter("orderId", request);
		try {
			User user = (User)request.getSession().getAttribute(Constant.STAFF_KEY);
			model.put("userId", user.getUserId());
			model.put("userName", user.getUserName());
			model.put("orderId", orderId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "agreement/agreementAdd";
	}

	@RequestMapping("/getAgreementAddNewDlg.do")
	public String getAgreementAddNewDlg(HttpServletRequest request,
									 HttpServletResponse response, Map<String, Object> model) {
		Integer orderId =  ServletAPIUtil.getIntegerParameter("orderId", request);
		try {
			User user = (User)request.getSession().getAttribute(Constant.STAFF_KEY);
			model.put("userId", user.getUserId());
			model.put("userName", user.getUserName());
			model.put("orderId", orderId);
			//获取对应的工位信息
			TaskTacheQuery taskTacheQuery = new TaskTacheQuery();
			taskTacheQuery.setOrderId(orderId);
			List<TaskTache> taskTacheList = this.taskTacheService.queryTaskTacheListOfOrder(taskTacheQuery);
			model.put("taskTacheList", new JSONArray(taskTacheList).toString());
			//获取工位对应的人员信息
			TacheUserQuery tacheUserQuery = new TacheUserQuery();
			tacheUserQuery.setOrderId(orderId);
			List<TacheUser> tacheUserList = this.tacheUserService.quereyTacheUserListOfOrder(tacheUserQuery);
			model.put("tacheUserList", new JSONArray(tacheUserList).toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "agreement/agreementAddNew";
	}
	
	@RequestMapping("/getTacheUser.do")
	public void getTacheUser(HttpServletRequest request,HttpServletResponse response, Map<String, Object> model){
		Integer tacheId =  ServletAPIUtil.getIntegerParameter("tacheId", request);
		try{
			List<User> userList = this.agreementService.queryTacheUser(tacheId);
			AjaxUtil.ajaxResponse(response, new JSONArray(userList).toString(), AjaxUtil.RESPONCE_TYPE_JSON);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * 获取追踪人员列表
	 * @param response 返回结果
	 */
	@RequestMapping("/getTrackUserList.do")
	public void getTrackUserList(HttpServletResponse response){
		try {
			List<User> userList = this.agreementService.queryTrackUser();
			AjaxUtil.ajaxResponse(response, new JSONArray(userList).toString(), AjaxUtil.RESPONCE_TYPE_JSON);
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	@RequestMapping("/getUser.do")
	public void getUser(HttpServletRequest request,HttpServletResponse response, Map<String, Object> model){
		try{
			List<User> userList = this.agreementService.queryUser();
			AjaxUtil.ajaxResponse(response, new JSONArray(userList).toString(), AjaxUtil.RESPONCE_TYPE_JSON);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/addAgreement.do")
	public void addAgreement(HttpServletRequest request,HttpServletResponse response, Map<String, Object> model){
		Map<String, Object> map = new HashMap<String, Object>();
		try{
			Integer orderId =  ServletAPIUtil.getIntegerParameter("orderId", request);
			Integer userId =  ServletAPIUtil.getIntegerParameter("userId", request);
			Integer agreementId = this.agreementService.addAgreement(request, orderId, userId);
			User user = (User)request.getSession().getAttribute(Constant.STAFF_KEY);
			this.historyService.addCopyAgreement(agreementId, user.getUserId(), "新增");
			map.put("success", true);
		}catch(Exception e){
			e.printStackTrace();
			map.put("success", false);
			map.put("message", e.getMessage());
		}
		AjaxUtil.ajaxResponse(response, new JSONObject(map).toString(), AjaxUtil.RESPONCE_TYPE_JSON);
	}
	
	@RequestMapping("/getAgreementState.do")
	public void getAgreementState(HttpServletRequest request,HttpServletResponse response, Map<String, Object> model){
		try{
			List<AgreementState> agreementStateList = this.agreementService.queryAgreementState();
			AjaxUtil.ajaxResponse(response, new JSONArray(agreementStateList).toString(), AjaxUtil.RESPONCE_TYPE_JSON);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/queryAgreementListJson.do")
	public void queryAgreementListJson(HttpServletRequest request,
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
			AgreementQuery query = this.agreementService.setAgreementQueryData(request);
			Page page = this.agreementService.queryAgreementPage(query, pageIndex, pageSize);
			AjaxUtil.ajaxResponse(response, JSONUtil.getJSONObject(page)
					.toString(), AjaxUtil.RESPONCE_TYPE_TEXT);
		} catch (ServiceException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/getAgreementContentDlg.do")
	public String getAgreementContentDlg(HttpServletRequest request,HttpServletResponse response, Map<String, Object> model){
		Integer orderId =  ServletAPIUtil.getIntegerParameter("orderId", request);
		Integer agreementId =  ServletAPIUtil.getIntegerParameter("agreementId", request);
		try {
			User user = (User)request.getSession().getAttribute(Constant.STAFF_KEY);
			Integer userId = user.getUserId();
			Agreement agreement = this.agreementService.queryAgreementInfo(orderId, agreementId, userId);
			List<User> userList = this.agreementService.queryUser();
			model.put("userList", new JSONArray(userList).toString());
			model.put("agreementJSON", new JSONObject(agreement));
			model.put("orderId", orderId);
			model.put("agreementId", agreementId);
			model.put("createUserId", agreement.getCreateUserId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "agreement/agreementContent";
	}
	
	@RequestMapping("/getAgreementConfirmDlg.do")
	public String getAgreementConfirmDlg(HttpServletRequest request,HttpServletResponse response, Map<String, Object> model){
		Integer orderId =  ServletAPIUtil.getIntegerParameter("orderId", request);
		Integer agreementId =  ServletAPIUtil.getIntegerParameter("agreementId", request);
		try {
			User user = (User)request.getSession().getAttribute(Constant.STAFF_KEY);
			Integer userId = user.getUserId();
			Agreement agreement = this.agreementService.queryAgreementInfo2(orderId, agreementId, userId);
			List<User> userList = this.agreementService.queryUser();
			model.put("userList", new JSONArray(userList).toString());
			model.put("agreementJSON", new JSONObject(agreement));
			model.put("orderId", orderId);
			model.put("agreementId", agreementId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "agreement/agreementConfirm";
	}
	
	@RequestMapping("/getAgreementResultDlg.do")
	public String getAgreementResultDlg(HttpServletRequest request,HttpServletResponse response, Map<String, Object> model){
		Integer orderId =  ServletAPIUtil.getIntegerParameter("orderId", request);
		Integer agreementId =  ServletAPIUtil.getIntegerParameter("agreementId", request);
		try {
			Agreement agreement = this.agreementService.queryAgreementInfo3(orderId, agreementId);
			List<User> userList = this.agreementService.queryUser();
			model.put("userList", new JSONArray(userList).toString());
			model.put("agreementJSON", new JSONObject(agreement));
			model.put("orderId", orderId);
			model.put("agreementId", agreementId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "agreement/agreementResult";
	}
	
	@RequestMapping("/updateAgreementContent.do")
	public void updateAgreementContent(HttpServletRequest request,HttpServletResponse response, Map<String, Object> model){
		Map<String, Object> map = new HashMap<String, Object>();
		try{
			Integer agreementId =  ServletAPIUtil.getIntegerParameter("agreementId", request);
			this.agreementService.updateAgreementContent(request);
			User user = (User)request.getSession().getAttribute(Constant.STAFF_KEY);
			this.historyService.addCopyAgreement(agreementId, user.getUserId(), "填写内容");
			map.put("success", true);
		}catch(Exception e){
			e.printStackTrace();
			map.put("success", false);
			map.put("message", e.getMessage());
		}
		AjaxUtil.ajaxResponse(response, new JSONObject(map).toString(), AjaxUtil.RESPONCE_TYPE_JSON);
	}
	
	@RequestMapping("/refuseAgreementContent.do")
	public void refuseAgreementContent(HttpServletRequest request,HttpServletResponse response, Map<String, Object> model){
		Map<String, Object> map = new HashMap<String, Object>();
		try{
			Integer orderId =  ServletAPIUtil.getIntegerParameter("orderId", request);
			Integer agreementId =  ServletAPIUtil.getIntegerParameter("agreementId", request);
			Integer id =  ServletAPIUtil.getIntegerParameter("id", request);
			String refuseReason = ServletAPIUtil.getStringParameter("refuseReason", request, "");
			this.agreementService.editRefuseAgreementContent(orderId, agreementId, id, refuseReason);
			User user = (User)request.getSession().getAttribute(Constant.STAFF_KEY);
			this.historyService.addCopyAgreement(agreementId, user.getUserId(), "拒绝");
			map.put("success", true);
			map.put("message", "成功！");
		}catch(Exception e){
			e.printStackTrace();
			map.put("success", false);
			map.put("message", e.getMessage());
		}
		AjaxUtil.ajaxResponse(response, new JSONObject(map).toString(), AjaxUtil.RESPONCE_TYPE_JSON);
	}
	
	@RequestMapping("/acceptAgreementContent.do")
	public void acceptAgreementContent(HttpServletRequest request,HttpServletResponse response, Map<String, Object> model){
		Map<String, Object> map = new HashMap<String, Object>();
		try{
			Integer orderId =  ServletAPIUtil.getIntegerParameter("orderId", request);
			Integer agreementId =  ServletAPIUtil.getIntegerParameter("agreementId", request);
			Integer id =  ServletAPIUtil.getIntegerParameter("id", request);
			String state = ServletAPIUtil.getStringParameter("state", request, "");
			this.agreementService.editAcceptAgreementContent(orderId, agreementId, id, state);
			this.agreementService.editCompleteAgreement(orderId, agreementId);
			User user = (User)request.getSession().getAttribute(Constant.STAFF_KEY);
			this.historyService.addCopyAgreement(agreementId, user.getUserId(), "确认");
			map.put("success", true);
			map.put("message", "成功！");
		}catch(Exception e){
			e.printStackTrace();
			map.put("success", false);
			map.put("message", e.getMessage());
		}
		AjaxUtil.ajaxResponse(response, new JSONObject(map).toString(), AjaxUtil.RESPONCE_TYPE_JSON);
	}
	
	@RequestMapping("/refuseAgreementContents.do")
	public void refuseAgreementContents(HttpServletRequest request,HttpServletResponse response, Map<String, Object> model){
		Map<String, Object> map = new HashMap<String, Object>();
		try{
			Integer orderId =  ServletAPIUtil.getIntegerParameter("orderId", request);
			Integer agreementId =  ServletAPIUtil.getIntegerParameter("agreementId", request);
			String refuseReason = ServletAPIUtil.getStringParameter("refuseReason", request, "");
			String idsStr =  ServletAPIUtil.getStringParameter("idsStr", request, "");
			if(!"".equals(idsStr)){
				String[]  idArray = idsStr.split(",");
				for(int i = 0; i < idArray.length; i++){
					int id = Integer.parseInt(idArray[i]);
					this.agreementService.editRefuseAgreementContent(orderId, agreementId, id, refuseReason);
				}
			}
			User user = (User)request.getSession().getAttribute(Constant.STAFF_KEY);
			this.historyService.addCopyAgreement(agreementId, user.getUserId(), "拒绝");
			map.put("success", true);
			map.put("message", "成功！");
		}catch(Exception e){
			e.printStackTrace();
			map.put("success", false);
			map.put("message", e.getMessage());
		}
		AjaxUtil.ajaxResponse(response, new JSONObject(map).toString(), AjaxUtil.RESPONCE_TYPE_JSON);
	}
	
	@RequestMapping("/acceptAgreementContents.do")
	public void acceptAgreementContents(HttpServletRequest request,HttpServletResponse response, Map<String, Object> model){
		Map<String, Object> map = new HashMap<String, Object>();
		try{
			Integer orderId =  ServletAPIUtil.getIntegerParameter("orderId", request);
			Integer agreementId =  ServletAPIUtil.getIntegerParameter("agreementId", request);
			String idsStr =  ServletAPIUtil.getStringParameter("idsStr", request, "");
			String stateStr =  ServletAPIUtil.getStringParameter("stateStr", request, "");
			if(!"".equals(idsStr)){
				String[]  idArray = idsStr.split(",");
				String[]  stateArray = stateStr.split(",");
				for(int i = 0; i < idArray.length; i++){
					int id = Integer.parseInt(idArray[i]);
					String state = stateArray[i];
					this.agreementService.editAcceptAgreementContent(orderId, agreementId, id, state);
				}
			}
			this.agreementService.editCompleteAgreement(orderId, agreementId);
			User user = (User)request.getSession().getAttribute(Constant.STAFF_KEY);
			this.historyService.addCopyAgreement(agreementId, user.getUserId(), "确认");
			map.put("success", true);
			map.put("message", "成功！");
		}catch(Exception e){
			e.printStackTrace();
			map.put("success", false);
			map.put("message", e.getMessage());
		}
		AjaxUtil.ajaxResponse(response, new JSONObject(map).toString(), AjaxUtil.RESPONCE_TYPE_JSON);
	}
	
	@RequestMapping("/agreementResultNG.do")
	public void agreementResultNG(HttpServletRequest request,HttpServletResponse response, Map<String, Object> model){
		Map<String, Object> map = new HashMap<String, Object>();
		try{
			Integer orderId =  ServletAPIUtil.getIntegerParameter("orderId", request);
			Integer agreementId =  ServletAPIUtil.getIntegerParameter("agreementId", request);
			String resultMessage = ServletAPIUtil.getStringParameter("resultMessage", request, "");
			this.agreementService.editAgreementResultNG(orderId, agreementId, resultMessage);
			map.put("success", true);
			map.put("message", "成功！");
		}catch(Exception e){
			e.printStackTrace();
			map.put("success", false);
			map.put("message", e.getMessage());
		}
		AjaxUtil.ajaxResponse(response, new JSONObject(map).toString(), AjaxUtil.RESPONCE_TYPE_JSON);
	}
	
	@RequestMapping("/agreementResultOK.do")
	public void agreementResultOK(HttpServletRequest request,HttpServletResponse response, Map<String, Object> model){
		Map<String, Object> map = new HashMap<String, Object>();
		try{
			Integer orderId =  ServletAPIUtil.getIntegerParameter("orderId", request);
			Integer agreementId =  ServletAPIUtil.getIntegerParameter("agreementId", request);
			this.agreementService.editAgreementResultOK(orderId, agreementId);
			map.put("success", true);
			map.put("message", "成功！");
		}catch(Exception e){
			e.printStackTrace();
			map.put("success", false);
			map.put("message", e.getMessage());
		}
		AjaxUtil.ajaxResponse(response, new JSONObject(map).toString(), AjaxUtil.RESPONCE_TYPE_JSON);
	}
	
	@RequestMapping("/getAgreementDetailDlg.do")
	public String getAgreementDetailDlg(HttpServletRequest request,HttpServletResponse response, Map<String, Object> model){
		Integer orderId =  ServletAPIUtil.getIntegerParameter("orderId", request);
		Integer agreementId =  ServletAPIUtil.getIntegerParameter("agreementId", request);
		try {
			Agreement agreement = this.agreementService.queryAgreementInfo3(orderId, agreementId);
			model.put("agreementJSON", new JSONObject(agreement));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "agreement/agreementDetail";
	}
	
	@RequestMapping("/getAgrementEditDlg.do")
	public String getAgrementEditDlg(HttpServletRequest request,HttpServletResponse response, Map<String, Object> model){
		Integer orderId =  ServletAPIUtil.getIntegerParameter("orderId", request);
		Integer agreementId =  ServletAPIUtil.getIntegerParameter("agreementId", request);
		try {
			Agreement agreement = this.agreementService.queryAgreementInfo3(orderId, agreementId);
			List<User> userList = this.agreementService.queryUser();
			model.put("userList", new JSONArray(userList).toString());
			model.put("agreementJSON", new JSONObject(agreement));
			model.put("orderId", orderId);
			model.put("agreementId", agreementId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "agreement/agreementEdit1";
	}
	
	@RequestMapping("/getAgrementEditDlg2.do")
	public String getAgrementEditDlg2(HttpServletRequest request,HttpServletResponse response, Map<String, Object> model){
		Integer orderId =  ServletAPIUtil.getIntegerParameter("orderId", request);
		Integer agreementId =  ServletAPIUtil.getIntegerParameter("agreementId", request);
		try {
			Agreement agreement = this.agreementService.queryAgreementInfo3(orderId, agreementId);
			List<User> userList = this.agreementService.queryUser();
			model.put("userList", new JSONArray(userList).toString());
			model.put("agreementJSON", new JSONObject(agreement));
			model.put("orderId", orderId);
			model.put("agreementId", agreementId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "agreement/agreementEdit2";
	}
	
	@RequestMapping("/eidtAgreement.do")
	public void eidtAgreement(HttpServletRequest request,HttpServletResponse response, Map<String, Object> model){
		Map<String, Object> map = new HashMap<String, Object>();
		try{
			this.agreementService.updateAgreement(request);
			Integer agreementId =  ServletAPIUtil.getIntegerParameter("agreementId", request);
			User user = (User)request.getSession().getAttribute(Constant.STAFF_KEY);
			this.historyService.addCopyAgreement(agreementId, user.getUserId(), "修改");
			map.put("success", true);
		}catch(Exception e){
			e.printStackTrace();
			map.put("success", false);
			map.put("message", e.getMessage());
		}
		AjaxUtil.ajaxResponse(response, new JSONObject(map).toString(), AjaxUtil.RESPONCE_TYPE_JSON);
	}
	
	@RequestMapping("/eidtAgreement2.do")
	public void eidtAgreement2(HttpServletRequest request,HttpServletResponse response, Map<String, Object> model){
		Map<String, Object> map = new HashMap<String, Object>();
		try{
			this.agreementService.updateAgreement(request);
			Integer agreementId =  ServletAPIUtil.getIntegerParameter("agreementId", request);
			User user = (User)request.getSession().getAttribute(Constant.STAFF_KEY);
			this.historyService.addCopyAgreement(agreementId, user.getUserId(), "修改");
			map.put("success", true);
		}catch(Exception e){
			e.printStackTrace();
			map.put("success", false);
			map.put("message", e.getMessage());
		}
		AjaxUtil.ajaxResponse(response, new JSONObject(map).toString(), AjaxUtil.RESPONCE_TYPE_JSON);
	}
	
	@RequestMapping("/delAgreement.do")
	public void delAgreement(HttpServletRequest request,HttpServletResponse response, Map<String, Object> model){
		Map<String, Object> map = new HashMap<String, Object>();
		try{
			Integer orderId =  ServletAPIUtil.getIntegerParameter("orderId", request);
			Integer agreementId =  ServletAPIUtil.getIntegerParameter("agreementId", request);
			String invalidateText = ServletAPIUtil.getStringParameter("invalidateText", request, "");
			this.agreementService.deleteAgreement(orderId, agreementId, invalidateText);
			User user = (User)request.getSession().getAttribute(Constant.STAFF_KEY);
			this.historyService.addCopyAgreement(agreementId, user.getUserId(), "作废");
			map.put("success", true);
		}catch(Exception e){
			e.printStackTrace();
			map.put("success", false);
			map.put("message", e.getMessage());
		}
		AjaxUtil.ajaxResponse(response, new JSONObject(map).toString(), AjaxUtil.RESPONCE_TYPE_JSON);
	}
}
