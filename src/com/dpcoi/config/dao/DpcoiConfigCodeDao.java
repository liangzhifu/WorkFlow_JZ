package com.dpcoi.config.dao;/**
 * Created by liangzhifu
 * DATE:2017/6/17.
 */

import com.dpcoi.config.domain.DpcoiConfigCode;
import com.success.web.framework.exception.DaoException;
import com.success.web.framework.mybatis.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * @author lzf
 * @create 2017-06-17
 **/
@Repository
public class DpcoiConfigCodeDao extends BaseDao {

    /**
     * 查询列表
     * @return 返回结果
     * @throws DaoException 异常
     */
    public List<DpcoiConfigCode> selectDpcoiConfigCodeList() throws DaoException{
        return this.sqlSession.selectList("dpcoiConfigCodeMapper.selectDpcoiConfigCodeList", null);
    }
}
