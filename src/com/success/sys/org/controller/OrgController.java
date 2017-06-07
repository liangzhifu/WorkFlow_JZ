package com.success.sys.org.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.success.sys.org.domain.Org;
import com.success.sys.org.domain.OrgFun;
import com.success.sys.org.query.OrgQuery;
import com.success.sys.org.service.OrgFunService;
import com.success.sys.org.service.OrgService;
import com.success.web.common.TreeNode;
import com.success.web.framework.exception.ServiceException;
import com.success.web.framework.mybatis.Page;
import com.success.web.framework.util.AjaxUtil;
import com.success.web.framework.util.JSONUtil;
import com.success.web.framework.util.ServletAPIUtil;

@Controller
@RequestMapping("/org")
public class OrgController {

	@Resource(name = "orgService")
	private OrgService orgService;
	@Resource(name = "orgFunService")
	private OrgFunService orgFunService;

	/**
	 * 获取组织列表
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("/queryPageOrg.do")
	public void queryPageOrg(HttpServletRequest request,
			HttpServletResponse response, Map<String, Object> model) {
		//
		// System.out.println(request.getCharacterEncoding());
		// System.out.println("orgName:"+ServletAPIUtil.getStringParameter("orgName",
		// request));
		// System.out.println("pageSize:"+request.getParameter("pageSize"));

		int pageIndex = ServletAPIUtil.getIntegerParameter("page", request, 1);
		int pageSize = ServletAPIUtil.getIntegerParameter("rows", request, 20);
		try {
			OrgQuery query = new OrgQuery();
			query = this.orgService.setOrgQueryData(request);
			Page page = this.orgService.getPageOrg(query, pageIndex, pageSize);
			AjaxUtil.ajaxResponse(response, JSONUtil.getJSONObject(page)
					.toString(), AjaxUtil.RESPONCE_TYPE_TEXT);
		} catch (ServiceException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/queryOrgListJson.do")
	public void queryUserListJson(HttpServletRequest request,
			HttpServletResponse response, Map<String, Object> model) {
		int start = ServletAPIUtil.getIntegerParameter("start", request, 0);
		int pageSize = ServletAPIUtil.getIntegerParameter("limit", request, 20);
		try {
			int pageIndex = 0;
			if (start == 0) {
				pageIndex = 1;
			} else {
				pageIndex = start / pageSize + 1;
			}
			OrgQuery query = new OrgQuery();
			query = this.orgService.setOrgQueryData(request);
			Page page = this.orgService.getPageOrg(query, pageIndex, pageSize);
			AjaxUtil.ajaxResponse(response, JSONUtil.getJSONObject(page)
					.toString(), AjaxUtil.RESPONCE_TYPE_TEXT);
		} catch (ServiceException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取组织树数据
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("/loadOrgTreeData.do")
	public void loadOrgTreeData(HttpServletRequest request,
			HttpServletResponse response) {
		Integer parentId = ServletAPIUtil.getIntegerParameter("parentId",
				request, 50);
		OrgQuery query = new OrgQuery();
		query.setParentId(parentId);
		List<Org> list = this.orgService.queryOrgs(query);
		List<TreeNode> orgNodes = new ArrayList<TreeNode>();
		for (Org org : list) {
			TreeNode orgNode = new TreeNode();
			Map<String, Object> attributes = new HashMap<String, Object>();
			attributes.put("orgId", org.getOrgId());
			orgNode.setId(Long.parseLong(org.getOrgId().toString()));
			orgNode.setText(org.getOrgName());
			orgNode.setAttributes(attributes);
			orgNode.setState("closed");
			orgNodes.add(orgNode);
		}
		JSONArray jresult = null;
		jresult = new JSONArray(orgNodes);
		AjaxUtil.ajaxResponse(response, jresult.toString(),
				AjaxUtil.RESPONCE_TYPE_TEXT);
	}

	/**
	 * 增加组织
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/addOrg.do")
	public void insertOrg(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		Integer parentId = ServletAPIUtil.getIntegerParameter("parentId",
				request, 30);
		String orgName = ServletAPIUtil.getStringParameter("orgName", request);
		String orgMark = ServletAPIUtil.getStringParameter("orgMark", request);
		String dpcoiAddJurisdiction = ServletAPIUtil.getStringParameter("dpcoiAddJurisdiction", request);
		String agreementTrack = ServletAPIUtil.getStringParameter("agreementTrack", request);
		String dpcoiQuaisAct = ServletAPIUtil.getStringParameter("dpcoiQuaisAct", request);
		String taskEditJurisdiction = ServletAPIUtil.getStringParameter("taskEditJurisdiction", request);
		Org org = new Org();
		OrgQuery query = new OrgQuery();
		OrgFun orgFun = new OrgFun();
		org.setOrgName(orgName);
		org.setParentId(parentId);
		org.setDpcoiAddJurisdiction(Integer.valueOf(dpcoiAddJurisdiction));
		org.setAgreementTrack(Integer.valueOf(agreementTrack));
		org.setDpcoiQuaisAct(Integer.valueOf(dpcoiQuaisAct));
		org.setTaskEditJurisdiction(Integer.valueOf(taskEditJurisdiction));
		org.setOrgMark(orgMark);
		String jsonStr = "";
		System.out.println("增加组织>>>>>" + org.toString());
		try {
			System.out.println("插入前orgId：" + org.getOrgId());
			Integer r_i = orgService.insertOrg(org);
			System.out.println("插入后orgId：" + org.getOrgId());
			orgService.updateOrgPath();
			query.setOrgId(org.getOrgId());
			org = orgService.selectOrgByIdQuery(query);
			String[] arrOrgPathCode = org.getOrgPathCode().split("\\.");
			Integer gradeId = arrOrgPathCode.length;
			org.setGradeId(gradeId);
			System.out.println("..........." + org.toString());
			orgService.updateOrg(org);
			orgFun.setFunId(gradeId);
			orgFun.setOrgId(org.getOrgId());
			orgFun.setOrgFun(org.getOrgName() + "-" + this.getOrgFun(gradeId));
			orgFunService.insertOrgFun(orgFun);
			if (r_i == 1) {
				jsonStr = "{'success':true,'message':'增加成功'}";
			} else {
				jsonStr = "{'success':false,'message':'增加失败'}";
			}
		} catch (ServiceException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.getWriter().write(jsonStr);
	}

	/**
	 * 修改组织
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/editOrg.do")
	public void updateOrg(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		Integer parentId = ServletAPIUtil.getIntegerParameter("parentId",
				request, 30);
		Integer orgId = ServletAPIUtil.getIntegerParameter("id", request);
		String orgName = ServletAPIUtil.getStringParameter("orgName", request);
		String orgMark = ServletAPIUtil.getStringParameter("orgMark", request);
		String dpcoiAddJurisdiction = ServletAPIUtil.getStringParameter("dpcoiAddJurisdiction", request);
		String agreementTrack = ServletAPIUtil.getStringParameter("agreementTrack", request);
		String dpcoiQuaisAct = ServletAPIUtil.getStringParameter("dpcoiQuaisAct", request);
		String taskEditJurisdiction = ServletAPIUtil.getStringParameter("taskEditJurisdiction", request);
		Org org = new Org();
		OrgQuery query = new OrgQuery();
		OrgFun orgFun = new OrgFun();
		org.setOrgId(orgId);
		org.setOrgName(orgName);
		org.setParentId(parentId);
		org.setOrgMark(orgMark);
		org.setDpcoiAddJurisdiction(Integer.valueOf(dpcoiAddJurisdiction));
		org.setAgreementTrack(Integer.valueOf(agreementTrack));
		org.setDpcoiQuaisAct(Integer.valueOf(dpcoiQuaisAct));
		org.setTaskEditJurisdiction(Integer.valueOf(taskEditJurisdiction));
		orgFun.setOrgId(orgId);
		query.setOrgId(orgId);
		String jsonStr = "";
		System.out.println("修改组织>>>>>" + org.toString());
		try {
			Integer r_i = orgService.updateOrg(org);
			orgService.updateOrgPath();
			org = orgService.selectOrgByIdQuery(query);
			System.out.println("............."+org.toString());
			orgFun.setOrgFun(org.getOrgName() + "-" + this.getOrgFun(org.getGradeId()));
			orgFunService.updateOrgFun(orgFun);
			if (r_i == 1) {
				jsonStr = "{'success':true,'message':'修改成功'}";
			} else {
				jsonStr = "{'success':false,'message':'修改失败'}";
			}
		} catch (ServiceException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.getWriter().write(jsonStr);
	}

	/**
	 * 删除组织
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/delOrg.do")
	public void deleteOrg(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		Integer orgId = ServletAPIUtil.getIntegerParameter("orgId", request);
		Org org = new Org();
		OrgFun orgFun = new OrgFun();
		OrgQuery orgQuery = new OrgQuery();
		orgQuery.setParentId(orgId);
		org.setOrgId(orgId);
		orgFun.setOrgId(orgId);
		String jsonStr = "";
		String message = "";
		boolean b = false;
		System.out.println("删除组织>>>>>" + org.toString());
		try {
			List<Org> list = this.orgService.queryOrgs(orgQuery);
			if (list.size() > 0) {
				message = "2";
				b = true;
			} else {
				Integer r_i = orgService.deleteOrg(org);
				orgFunService.deleteOrgFun(orgFun);
				if (r_i == 1) {
					message = "1";
					b = true;
				}
			}

			if (b) {
				jsonStr = "{'success':true,'message':'" + message + "'}";
			} else {
				jsonStr = "{'success':false,'message':'删除失败'}";
			}
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		response.getWriter().write(jsonStr);
	}

	public String getOrgFun(Integer gradeId) {
		String s = "";
		if (gradeId == 1) {
			s = "最大权限";
		} else if (gradeId == 2) {
			s = "部长权限";
		} else if (gradeId == 3) {
			s = "科长权限";
		} else {
			s = "其他权限";
		}
		return s;
	}
}
