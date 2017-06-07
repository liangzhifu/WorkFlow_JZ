package com.success.sys.user.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.success.sys.user.domain.User;
import com.success.sys.user.query.UserQuery;
import com.success.web.framework.exception.DaoException;
import com.success.web.framework.mybatis.BaseDao;
import com.success.web.framework.mybatis.Page;

@Repository("userDao")
public class UserDao extends BaseDao {

	/**
	 * 查询用户密码
	 * @param query
	 * @return
	 * @throws DaoException
	 */
	public User queryPasswordInfo(UserQuery query) throws DaoException{
		return this.sqlSession.selectOne("user.selectPassword", query);
	}
	
	/**
	 * 查询用户，只能查询出一个用户，以用户的唯一标识进行查询
	 * @param query
	 * @return
	 * @throws DaoException
	 */
	public User selectByQuery(UserQuery query) throws DaoException{
		return this.sqlSession.selectOne("user.selectByQuery", query);
	}
	/**
	 * 查询用户
	 * @param query
	 * @return
	 */
	public List<User> selectUser(UserQuery query){
		return this.sqlSession.selectList("user.selectByQuery", query);
	}
	
	public List<User> selectUserByCode(UserQuery query){
		return this.sqlSession.selectList("user.selectUserByCode", query);
	}
	
	public Page selectPageUser(UserQuery query, int pageIndex, int pageSize){
		return this.queryForPage("user.selectByQuery", query, pageIndex, pageSize);
	}
	/**
	 * 增加用户
	 */
	public Integer insertUser(User user){
		if(user == null) return -1;
		return this.sqlSession.insert("user.insertUser", user);
	}
	/**
	 * 更新User数据
	 * @param user
	 * @return
	 */
	public Integer updateUser(User user){
		if(user == null) return -1;
		return this.sqlSession.update("user.updateUser", user);
	}
	/**
	 * 删除User数据
	 * @param user
	 * @return
	 */
	public Integer deleteUser(User user){
		return this.sqlSession.delete("user.deleteUser", user);
	}
	
	/**
	 * 查询审核组织用户
	 * @param query
	 * @return
	 */
	public List<User> selectVerifyUser(){
		return this.sqlSession.selectList("user.selectVerifyUser");
	}
	
	public List<User> selectOrderContractUser(){
		return this.sqlSession.selectList("user.selectOrderContractUser");
	}
}
