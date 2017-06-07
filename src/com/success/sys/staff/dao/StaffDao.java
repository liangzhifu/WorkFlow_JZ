package com.success.sys.staff.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.success.sys.staff.domain.Staff;
import com.success.sys.staff.query.StaffQuery;
import com.success.sys.user.domain.User;
import com.success.web.framework.exception.DaoException;
import com.success.web.framework.mybatis.BaseDao;
import com.success.web.framework.mybatis.Page;

@Repository("staffDao")
public class StaffDao extends BaseDao {

	public Page selectPageStaff(StaffQuery query, int pageIndex, int pageSize){
		return this.queryForPage("staff.selectStaff", query, pageIndex, pageSize);
	}

	public List<Staff> selectStaff(StaffQuery query) throws DaoException{
		return this.sqlSession.selectList("staff.selectStaff", query);
	}
	
	/**
	 * 插入Staff数据
	 * @param org
	 * @return
	 */
	public Integer insertStaff(Staff org){
		if(org == null) return -1;
		return this.sqlSession.insert("staff.insertStaff", org);
	}
	
	/**
	 * 更新Staff数据
	 * @param org
	 * @return
	 */
	public Integer updateStaff(Staff org){
		if(org == null) return -1;
		return this.sqlSession.update("staff.updateStaff", org);
	}
	
	/**
	 * 删除Staff数据
	 * @param org
	 * @return
	 */
	public Integer deleteStaff(Staff org){
		return this.sqlSession.delete("staff.deleteStaff", org);
	}
	
	/**
	 * 查询指定ID的 Staff Domain对象
	 * @param id
	 * @return
	 * @throws DaoException
	 */
	public User selectStaffByIdQuery(StaffQuery query) throws DaoException{
		return this.sqlSession.selectOne("staff.selectByIdQuery", query);
	}
	
}
