package com.dpcoi.file.service.serviceImpl;/**
 * Created by liangzhifu
 * DATE:2017/5/7.
 */

import com.dpcoi.file.dao.FileUploadDao;
import com.dpcoi.file.domain.FileUpload;
import com.dpcoi.file.service.FileUploadService;
import com.success.sys.user.domain.User;
import com.success.web.framework.exception.ServiceException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

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

    @Override
    public FileUpload addUploadFile(MultipartFile file, String path, User user) throws Exception {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String fileName = file.getOriginalFilename();
        String fileType = file.getContentType();
        int index = fileName.lastIndexOf(".");
        String fileSuffix = fileName.substring(index);
        String fileAlias = UUID.randomUUID().toString() + fileSuffix;
        String filePath = path + "fileupload/" + fileAlias;
        file.transferTo(new File(filePath));

        FileUpload fileUpload = new FileUpload();
        fileUpload.setFileName(fileName);
        fileUpload.setFileAlias(fileAlias);
        fileUpload.setFileType(fileType);
        fileUpload.setExcelPdfName("");
        fileUpload.setCreateDate(new Date());
        fileUpload.setCreateBy(user.getUserId());
        this.fileUploadDao.insertFileUpload(fileUpload);
        return fileUpload;
    }
}
