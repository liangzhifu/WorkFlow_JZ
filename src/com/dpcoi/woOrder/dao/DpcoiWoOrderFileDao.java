package com.dpcoi.woOrder.dao;/**
 * Created by liangzhifu
 * DATE:2017/5/5.
 */

import com.dpcoi.woOrder.domain.DpcoiWoOrderFile;
import com.dpcoi.woOrder.query.DpcoiWoOrderFileQuery;
import com.success.web.framework.mybatis.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 *
 * @author lzf
 * @create 2017-05-05
 **/
@Repository
public class DpcoiWoOrderFileDao extends BaseDao {

    /**
     * 插入一个DpcoiWoOrderFile
     * @param dpcoiWoOrderFile 实体数据
     * @return 1成功，0不成功
     */
    public Integer insertDpcoiWoOrderFile(DpcoiWoOrderFile dpcoiWoOrderFile){
        return this.sqlSession.insert("dpcoiWoOrderFile.insertSelective", dpcoiWoOrderFile);
    }

    /**
     * 更新一个DpcoiWoOrderFile
     * @param dpcoiWoOrderFile 实体数据
     * @return 1成功，0不成功
     */
    public Integer updateDpcoiWoOrderFile(DpcoiWoOrderFile dpcoiWoOrderFile){
        return this.sqlSession.update("dpcoiWoOrderFile.updateSelective", dpcoiWoOrderFile);
    }

    /**
     * 获取dpcoiWoOrder对应的文件列表
     * @param dpcoiWoOrderFileQuery 查询条件
     * @return 返回结果
     */
    public List<Map<String, Object>> selectDpcoiWoOrderFileList(DpcoiWoOrderFileQuery dpcoiWoOrderFileQuery){
        return this.sqlSession.selectList("dpcoiWoOrderFile.selectDpcoiWoOrderFileList", dpcoiWoOrderFileQuery);
    }

    /**
     * 获取dpcoiOrder对应的文件列表
     * @param dpcoiWoOrderFileQuery 查询条件
     * @return 返回结果
     */
    public List<Map<String, Object>> selectDpcoiOrderFileList(DpcoiWoOrderFileQuery dpcoiWoOrderFileQuery){
        return this.sqlSession.selectList("dpcoiWoOrderFile.selectDpcoiOrderFileList", dpcoiWoOrderFileQuery);
    }

    /**
     * 获取dpcoiWoOrderFile文件数量
     * @param dpcoiWoOrderFileQuery 查询条件
     * @return 返回结果
     */
    public Integer selectDpcoiWoOrderFileCount(DpcoiWoOrderFileQuery dpcoiWoOrderFileQuery){
        return this.sqlSession.selectOne("dpcoiWoOrderFile.selectDpcoiWoOrderFileCount", dpcoiWoOrderFileQuery);
    }

    /**
     * 获取dpcoiWoOrderFile文件列表--分页
     * @param dpcoiWoOrderFileQuery 查询条件
     * @return 返回结果
     */
    public List<Map<String, Object>> selectDpcoiWoOrderFileListPage(DpcoiWoOrderFileQuery dpcoiWoOrderFileQuery){
        return this.sqlSession.selectList("dpcoiWoOrderFile.selectDpcoiWoOrderFileListPage", dpcoiWoOrderFileQuery);
    }

    /**
     * 删除工单中审核不通过的文件
     * @param dpcoiWoOrderFileQuery 查询条件
     * @return 返回结果
     */
    public Integer deleteDpcoiWoOrderFile(DpcoiWoOrderFileQuery dpcoiWoOrderFileQuery){
        return this.sqlSession.delete("dpcoiWoOrderFile.deleteDpcoiWoOrderFile", dpcoiWoOrderFileQuery);
    }

    /**
     * 更新工单对应文件中未审核的文件为审核通过
     * @param dpcoiWoOrderFileQuery 查询条件
     * @return 返回结果
     */
    public Integer updateDpcoiWoOrderFilePassed(DpcoiWoOrderFileQuery dpcoiWoOrderFileQuery){
        return this.sqlSession.update("dpcoiWoOrderFile.updateDpcoiWoOrderFilePassed", dpcoiWoOrderFileQuery);
    }

    /**
     * 将旧工单的关联关系添加到新工单
     * @param map 条件
     * @return 返回结果
     */
    public Integer insertCopyDpcoiWoOrder(Map<String, Object> map){
        return this.sqlSession.insert("dpcoiWoOrderFile.insertCopyDpcoiWoOrder", map);
    }
}
