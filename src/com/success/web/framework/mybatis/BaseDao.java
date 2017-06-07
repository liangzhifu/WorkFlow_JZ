package com.success.web.framework.mybatis;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

/**
 * Dao基类
 * @author liang.zhifu
 *
 */
public abstract class BaseDao {

	@Resource(name="sqlSession")
	protected SqlSession sqlSession;
	
	/**
	 * 分页查询
	 * @param statementName:sqlMap的语句Id
	 * @param query: 查询对象
	 * @param pageIndex：当前分页数量
	 * @param pageSize：分页限制数量
	 * @return
	 */
	protected Page queryForPage(String statementName, Object query, int pageIndex, int pageSize) {
		return queryForPage(statementName, query, pageIndex, pageSize,statementName + "Count");
	}

	/**
	 * 分页查询
	 * @param statementName:sqlMap的语句Id
	 * @param query: 查询对象
	 * @param pageIndex：当前分页数量
	 * @param pageSize：分页限制数量
	 * @param countStatementName: 查询记录总数的SQLMap语句ID
	 * @return
	 */
	protected Page queryForPage(String statementName, Object query, int pageIndex, int pageSize, String countStatementName) {
		if (countStatementName == null)
			countStatementName = statementName + "Count";
		
		long i = (Long) this.sqlSession.selectOne(countStatementName, query);
		int j = pageIndex - 1;
		j = j < 0 ? 0 : j;
		pageIndex = pageIndex < 1 ? 1 : pageIndex;
		int k = 0;
		k = j * (pageSize < 1 ? 1 : pageSize);
		List<Object> localList = this.sqlSession.selectList(statementName,query, new RowBounds(k, pageSize));
		return new Page(localList, pageSize, i, pageIndex);
	}
	
}
