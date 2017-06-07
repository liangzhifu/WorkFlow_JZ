package com.success.task.statistics.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.success.task.detail.domain.Detail;
import com.success.web.framework.exception.DaoException;
import com.success.web.framework.mybatis.BaseDao;

@Repository("statisticsManifestDao")
public class StatisticsManifestDao extends BaseDao {

	public Long selectManifestCount(String param) throws DaoException{
		return this.sqlSession.selectOne("statisticsManifest.selectManifestCount", param);
	}
	
	public List<Detail> selectDetailForAll(String param) {
		return this.sqlSession.selectList("statisticsManifest.selectDetailForAll", param);
	}
	
}
