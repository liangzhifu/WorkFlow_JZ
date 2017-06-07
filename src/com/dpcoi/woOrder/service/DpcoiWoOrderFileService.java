package com.dpcoi.woOrder.service;

import com.dpcoi.woOrder.domain.DpcoiWoOrderFile;
import com.dpcoi.woOrder.query.DpcoiWoOrderFileQuery;
import com.dpcoi.woOrder.query.DpcoiWoOrderQuery;
import com.success.sys.user.domain.User;
import com.success.web.framework.exception.ServiceException;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * Created by liangzhifu
 * DATE:2017/5/5.
 */
public interface DpcoiWoOrderFileService {
    /**
     * 添加一个dpcoiWoOrderFile
     * @param dpcoiWoOrderFile 实体数据
     * @return 返回结果
     * @throws ServiceException 异常信息
     */
    public Integer addDpcoiWoOrderFile(DpcoiWoOrderFile dpcoiWoOrderFile) throws ServiceException;

    /**
     * 更新一个dpcoiWoOrderFile
     * @param dpcoiWoOrderFile 实体数据
     * @return 返回结果
     * @throws ServiceException 异常信息
     */
    public Integer updateDpcoiWoOrderFile(DpcoiWoOrderFile dpcoiWoOrderFile) throws ServiceException;

    /**
     * 获取工单的所有文件
     * @param dpcoiWoOrderFileQuery 查询条件
     * @return 返回结果
     * @throws ServiceException 异常
     */
    public List<Map<String, Object>> queryDpcoiWoOrderFileList(DpcoiWoOrderFileQuery dpcoiWoOrderFileQuery) throws ServiceException;

    /**
     * 获取定单的所有通过审核文件
     * @param dpcoiWoOrderFileQuery 查询条件
     * @return 返回结果
     * @throws ServiceException 异常
     */
    public List<Map<String, Object>> queryDpcoiOrderFileList(DpcoiWoOrderFileQuery dpcoiWoOrderFileQuery) throws ServiceException;

    /**
     * 上传Dpcoi工单文件
     * @param dpcoiWoOrderFileId 工单ID
     * @param file 文件
     * @user 操作人
     * @throws ServiceException 异常
     */
    public void editWoOrderUploadFile(Integer dpcoiWoOrderFileId, MultipartFile file, String path, User user) throws ServiceException, Exception;

    /**
     * 获取dpcoiWoOrderFile文件列表--分页
     * @param dpcoiWoOrderFileQuery 查询条件
     * @return 返回结果
     * @throws ServiceException 异常
     */
    public List<Map<String, Object>> queryDpcoiWoOrderFileListPage(DpcoiWoOrderFileQuery dpcoiWoOrderFileQuery) throws ServiceException;

    /**
     * 获取dpcoiWoOrderFile文件数量
     * @param dpcoiWoOrderFileQuery 查询条件
     * @return 返回结果
     * @throws ServiceException 异常
     */
    public Integer queryDpcoiWoOrderFileCount(DpcoiWoOrderFileQuery dpcoiWoOrderFileQuery) throws ServiceException;
    
}
