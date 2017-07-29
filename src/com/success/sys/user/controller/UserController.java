package com.success.sys.user.controller;

import java.io.IOException;
import java.util.HashMap;
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
import com.success.sys.user.domain.User;
import com.success.sys.user.query.UserQuery;
import com.success.sys.user.service.UserService;
import com.success.sys.webservice.service.AsmxClient;
import com.success.sys.webservice.service.serviceImpl.AsmxClientImpl;
import com.success.web.framework.exception.ServiceException;
import com.success.web.framework.mybatis.Page;
import com.success.web.framework.util.AjaxUtil;
import com.success.web.framework.util.JSONUtil;
import com.success.web.framework.util.ServletAPIUtil;

@Controller
@RequestMapping("/user")
public class UserController {
	@Resource(name = "userService")
	private UserService userService;
	
	/**
	 * 获取用户列表
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("/queryPageUser.do")
	public void queryPageUser(HttpServletRequest request,
			HttpServletResponse response, Map<String, Object> model) {
		int pageIndex = ServletAPIUtil.getIntegerParameter("page", request, 1);
		int pageSize = ServletAPIUtil.getIntegerParameter("rows", request, 20);
		try {
			UserQuery query = new UserQuery();
			query = this.userService.setUserQueryData(request);
			System.out.println("传递的user查询条件是："+query.toString());
			Page page = this.userService.getPageUser(query, pageIndex, pageSize);
			AjaxUtil.ajaxResponse(response, JSONUtil.getJSONObject(page)
					.toString(), AjaxUtil.RESPONCE_TYPE_TEXT);
		} catch (ServiceException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	
	@RequestMapping("/queryUserListJson.do")
	public void queryUserListJson(HttpServletRequest request,
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
			UserQuery query = new UserQuery();
			query = this.userService.setUserQueryData(request);
			Page page = this.userService.getPageUser(query, pageIndex, pageSize);
			AjaxUtil.ajaxResponse(response, JSONUtil.getJSONObject(page)
					.toString(), AjaxUtil.RESPONCE_TYPE_TEXT);
		} catch (ServiceException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 增加人员
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/addUser.do")
	public void insertUser(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		User user = this.getUser(request);
		String jsonStr = "";
		System.out.println("增加人员>>>>>"+user.toString());
		try {
			Integer r_i = userService.insertUser(user);
			if (r_i == 1) {
				jsonStr = "{'success':true,'message':'增加成功'}";
			} else {
				jsonStr = "{'success':false,'message':'增加失败'}";
			}
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		response.getWriter().write(jsonStr);
	}
	
	@RequestMapping("/addUser2.do")
	public void insertUser2(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String jsonStr = "";
		try {
			String userCode = request.getParameter("userCode");
			UserQuery userQuery = new UserQuery();
			userQuery.setUserCode(userCode);
			List<User> userList = this.userService.queryUserByCode(userQuery);
			AsmxClient asmxClient = new AsmxClientImpl();
			if(userList == null || userList.size() == 0){
				String orgId = request.getParameter("orgId");
				String returnInfo = asmxClient.createAccountService(userCode);
				JSONObject jsonObj =  new JSONObject(returnInfo);
				System.out.println("增加："+returnInfo);
				String staff_info = (String)jsonObj.get("staff_info");
				if("0".equals(staff_info) || "2".equals(staff_info)){
					boolean flag = true;
					if("2".equals(staff_info)){
						returnInfo = asmxClient.updateAccountService(userCode, "0");
						System.out.println("更新："+returnInfo);
						jsonObj =  new JSONObject(returnInfo);
						staff_info = (String)jsonObj.get("staff_info");
						if("0".equals(staff_info)){
							
						}else {
							flag = false;
							jsonStr = "{'success':false,'message':'更新OA用户关联失败！'}";
						}
					}
					if(flag){
						returnInfo = asmxClient.staffInfoService(userCode);
						if(returnInfo.length() > 20){
							jsonObj =  new JSONObject(returnInfo);
				    		JSONArray jsonArray = (JSONArray)jsonObj.get("staff_info");
				    		JSONObject staffJsonObj = jsonArray.getJSONObject(0);
				    		//String account_staffid = (String)staffJsonObj.get("account_staffid");
				    		//String account_site = (String)staffJsonObj.get("account_site");
				    		String account_name = (String)staffJsonObj.get("account_name");
				    		String account_mobile = (String)staffJsonObj.get("account_mobile");
				    		String account_email = (String)staffJsonObj.get("account_email");
				    		//String account_company = (String)staffJsonObj.get("account_company");
				    		//String account_depart = (String)staffJsonObj.get("account_depart");
				    		//String account_position = (String)staffJsonObj.get("account_position");
				    		User user = new User();
				    		user.setUserCode(userCode);
				    		user.setUserName(account_name);
				    		user.setMobileTel(account_mobile);
				    		user.setEmail(account_email);
				    		user.setOrgId(Integer.valueOf(orgId));
				    		user.setUserWorkId(userCode);
				    		Integer r_i = userService.insertUser(user);
							if (r_i == 1) {
								jsonStr = "{'success':true,'message':'增加成功'}";
							} else {
								jsonStr = "{'success':false,'message':'增加失败'}";
							}
						}else {
							jsonStr = "{'success':false,'message':'获取OA用户失败'}";
						}
					}
				}else {
					jsonStr = "{'success':false,'message':'增加OA关联失败'}";
				}	
			}else if(userList.size() == 1){
				User user = userList.get(0);
				Integer deleteState = user.getDeleteState();
				if(deleteState == 0){
					jsonStr = "{'success':false,'message':'已有此工号的用户！'}";
				}else {
					User newUser = new User();
					newUser.setUserId(user.getUserId());
					newUser.setDeleteState(0);
					this.userService.updateUser(newUser);
					String returnInfo = asmxClient.updateAccountService(userCode, "0");
					JSONObject jsonObj =  new JSONObject(returnInfo);
					String staff_info = (String)jsonObj.get("staff_info");
					if("0".equals(staff_info)){
						
					}else {
						jsonStr = "{'success':false,'message':'更新OA用户关联失败！'}";
					}
				}
			}else {
				jsonStr = "{'success':false,'message':'系统中有此工号的重复数据！'}";
			}
		} catch (Exception e) {
			e.printStackTrace();
			jsonStr = "{'success':false,'message':'"+e.getMessage()+"'}";
		}
		response.getWriter().write(jsonStr);
	}
	
	/**
	 * 修改人员
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/editUser.do")
	public void updateUser(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		User user = this.getUser(request);
		String jsonStr = "";
		System.out.println("修改人员>>>>>"+user.toString());
		System.out.println(".>>>>>>>>>>"+user.toString());

		try {
			Integer r_i = userService.updateUser(user);
			if (r_i == 1) {
				jsonStr = "{'success':true,'message':'修改成功'}";
			} else {
				jsonStr = "{'success':false,'message':'修改失败'}";
			}
		} catch (ServiceException e) {
			jsonStr = "{'success':false,'message':'修改失败'}";
			e.printStackTrace();
		}
		response.getWriter().write(jsonStr);
	}
	/**
	 * 修改人员组织
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/editUserOrg.do")
	public void updateUserOrg(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		User user = new User();
		if((request.getParameter("id")!=null)&&(!request.getParameter("id").equals(""))){
			Integer userId = Integer.parseInt(request.getParameter("id"));
			user.setUserId(userId);
		}
		if((request.getParameter("orgId")!=null)&&(!request.getParameter("orgId").equals(""))){
			Integer orgId = Integer.parseInt(request.getParameter("orgId"));
			user.setOrgId(orgId);
		}
	
		String jsonStr = "";
		System.out.println("修改人员组织>>>>>"+user.toString());
		try {
			Integer r_i = userService.updateUser(user);
			if (r_i == 1) {
				jsonStr = "{'success':true,'message':'修改成功'}";
			} else {
				jsonStr = "{'success':false,'message':'修改失败'}";
			}
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		response.getWriter().write(jsonStr);
	}
	/**
	 * 删除人员
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/delUser.do")
	public void deleteUser(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		Integer userId = ServletAPIUtil.getIntegerParameter("userId", request);
		String jsonStr = "";
		try {
			AsmxClient AsmxClient = new AsmxClientImpl();
			UserQuery userQuery = new UserQuery();
			userQuery.setUserId(userId);
			User user = this.userService.queryUser(userQuery);
			String returnInfo = AsmxClient.updateAccountService(user.getUserCode(), "1");
			JSONObject jsonObj =  new JSONObject(returnInfo);
			String staff_info = (String)jsonObj.get("staff_info");
			if("0".equals(staff_info)){
				Integer r_i = userService.deleteUser(user);
				if (r_i == 1 ) {
					jsonStr = "{'success':true,'message':'删除成功'}";
				} else {
					jsonStr = "{'success':false,'message':'删除失败'}";
				}
			}else {
				jsonStr = "{'success':false,'message':'删除OA系统关联失败'}";
			}
		} catch (Exception e) {
			e.printStackTrace();
			jsonStr = "{'success':false,'message':'删除失败'}";
		}
		response.getWriter().write(jsonStr);
	}
	
	@RequestMapping("/editPassword.do")
	public void editPassword(HttpServletRequest request,HttpServletResponse response, Map<String, Object> model){
		Map<String, Object> map = new HashMap<String, Object>();
		try{
			User user = (User)request.getSession().getAttribute(Constant.STAFF_KEY);
			String password = ServletAPIUtil.getStringParameter("password", request);
			User newUser = new User();
			newUser.setPassword(password);
			newUser.setUserId(user.getUserId());
			this.userService.editPassword(newUser);
			map.put("success", true);
			map.put("message", "修改成功！");
		}catch(Exception e){
			e.printStackTrace();
			map.put("success", true);
			map.put("message", e.getMessage());
		}
		AjaxUtil.ajaxResponse(response, new JSONObject(map).toString(), AjaxUtil.RESPONCE_TYPE_JSON);
	}
	
	public User getUser(HttpServletRequest request){
		User user = new User();
		Integer userId = null;
		if((request.getParameter("id")!=null)&&(!request.getParameter("id").equals(""))){
			userId = Integer.parseInt(request.getParameter("id"));
		}
		user.setUserId(userId);
		user.setUserName(request.getParameter("userName"));
		user.setUserSex(request.getParameter("userSex"));
		user.setUserWorkId(request.getParameter("userWorkId"));
		user.setUserCode(request.getParameter("userCode"));
		user.setPassword(request.getParameter("password")==null||request.getParameter("password").equals("")?"111111":request.getParameter("password"));
		user.setOrgId(Integer.parseInt(request.getParameter("orgId")));
		user.setEmail(request.getParameter("email"));
		user.setMobileTel(request.getParameter("mobileTel"));
		user.setUserMark(request.getParameter("userMark"));	
		user.setIsHeader(request.getParameter("isHeader"));
		user.setRrProblemPersionliable(request.getParameter("rrProblemPersionliable"));
		user.setRrMinister(request.getParameter("rrMinister"));
		return user;
	}

}
