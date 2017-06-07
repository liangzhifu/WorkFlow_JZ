package com.success.sys.staff.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;

import com.success.sys.staff.domain.Staff;
import com.success.sys.staff.query.StaffQuery;
import com.success.web.framework.exception.ServiceException;
import com.success.web.framework.mybatis.Page;

public interface StaffService {

	public Page getPageStaff(StaffQuery query, int pageIndex, int pageSize) throws ServiceException;
	
	public List<Staff> queryStaffs(StaffQuery query);
	
	public JSONArray queryStaffList(StaffQuery query) throws ServiceException;
	
	public Integer insertStaff(Staff staff) throws ServiceException;
	
	public Integer updateStaff(Staff staff) throws ServiceException;
	
	public Integer deleteStaff(Staff staff) throws ServiceException;
	
	public Staff selectStaffByIdQuery(StaffQuery query) throws Exception;
	
	public StaffQuery setStaffQueryData(HttpServletRequest request);
	
}
