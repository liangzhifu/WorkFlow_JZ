package com.dpcoi.file.service.serviceImpl;/**
 * Created by liangzhifu
 * DATE:2017/5/7.
 */

import com.dpcoi.file.dao.FileUploadDao;
import com.dpcoi.file.domain.FileUpload;
import com.dpcoi.file.service.FileUploadService;
import com.success.web.framework.exception.ServiceException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 *
 * @author lzf
 * @create 2017-05-07
 **/

@Service("fileUploadService")
public class FileUploadServiceImpl implements FileUploadService {

    @Resource(name="fileUploadDao")
    private FileUploadDao fileUploadDao;

    @Override
    public FileUpload queryFileUpload(FileUpload fileUpload) throws ServiceException {
        return this.fileUploadDao.selectBySelf(fileUpload);
    }
}
