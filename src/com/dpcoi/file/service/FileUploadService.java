package com.dpcoi.file.service;/**
 * Created by liangzhifu
 * DATE:2017/5/7.
 */

import com.dpcoi.file.domain.FileUpload;
import com.success.sys.user.domain.User;
import com.success.web.framework.exception.ServiceException;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author lzf
 * @create 2017-05-07
 **/

public interface FileUploadService {

    /**
     * 获取文件实体
     * @param fileUpload 实体ID
     * @return 返回结果
     * @throws ServiceException 异常
     */
    public FileUpload queryFileUpload(FileUpload fileUpload) throws ServiceException;

    /**
     * 上传文件
     * @param file
     * @param path
     * @return
     * @throws Exception
     */
    public FileUpload addUploadFile(MultipartFile file, String path, User user) throws Exception;
}
