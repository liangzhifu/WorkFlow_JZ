package com.dpcoi.config.service;

import com.dpcoi.config.domain.DpcoiConfig;
import com.dpcoi.config.query.DpcoiConfigQuery;
import com.success.web.framework.exception.ServiceException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.util.List;
import java.util.Map;

/**
 * Created by liangzhifu
 * DATE:2017/6/17.
 */
public interface DpcoiConfigService {

    /**
     * 添加
     * @param dpcoiConfig 参数
     * @return 返回结果
     * @throws ServiceException 异常
     */
    Integer addDpcoiConfig(DpcoiConfig dpcoiConfig) throws ServiceException;

    /**
     * 删除
     * @param dpcoiConfig 参数
     * @return 返回结果
     * @throws ServiceException 异常
     */
    Integer deleteDpcoiConfig(DpcoiConfig dpcoiConfig) throws ServiceException;

    /**
     * 查询列表
     * @param dpcoiConfigQuery 查询条件
     * @return 返回结果
     * @throws ServiceException 异常
     */
    List<Map<String, Object>> queryDpcoiConfigList(DpcoiConfigQuery dpcoiConfigQuery) throws ServiceException;

    /**
     * 查询分页列表
     * @param dpcoiConfigQuery 查询条件
     * @return 返回结果
     * @throws ServiceException 异常
     */
    List<Map<String, Object>> queryDpcoiConfigPageList(DpcoiConfigQuery dpcoiConfigQuery) throws ServiceException;

    /**
     * 查询总数
     * @param dpcoiConfigQuery 查询条件
     * @return 返回结果
     * @throws ServiceException 异常
     */
    Integer queryDpcoiConfigCount(DpcoiConfigQuery dpcoiConfigQuery) throws ServiceException;

    /**
     * 解析Excle
     * @param wb Excel文件
     * @throws ServiceException 异常
     */
    void addUploadFile(HSSFWorkbook wb) throws ServiceException;

    /**
     * 导出Excel文件
     * @param dpcoiConfigQuery 查询条件
     * @param path 项目路径
     * @return 返回结果
     * @throws Exception 异常
     */
    String doExportExcle(DpcoiConfigQuery dpcoiConfigQuery, String path) throws Exception;
}
