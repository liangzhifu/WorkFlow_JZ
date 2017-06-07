package com.dpcoi.woOrder.service.serviceImpl;/**
 * Created by liangzhifu
 * DATE:2017/5/5.
 */

import com.dpcoi.file.dao.FileUploadDao;
import com.dpcoi.file.domain.FileUpload;
import com.dpcoi.util.TransferToolUtil;
import com.dpcoi.woOrder.dao.DpcoiWoOrderDao;
import com.dpcoi.woOrder.dao.DpcoiWoOrderFileDao;
import com.dpcoi.woOrder.domain.DpcoiWoOrderFile;
import com.dpcoi.woOrder.query.DpcoiWoOrderFileQuery;
import com.dpcoi.woOrder.service.DpcoiWoOrderFileService;
import com.success.sys.user.domain.User;
import com.success.web.framework.exception.ServiceException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 *
 * @author lzf
 * @create 2017-05-05
 **/
@Service("dpcoiWoOrderFileService")
public class DpcoiWoOrderFileServiceImpl implements DpcoiWoOrderFileService {

    @Resource(name="dpcoiWoOrderFileDao")
    private DpcoiWoOrderFileDao dpcoiWoOrderFileDao;

    @Resource(name="fileUploadDao")
    private FileUploadDao fileUploadDao;

    @Override
    public Integer addDpcoiWoOrderFile(DpcoiWoOrderFile dpcoiWoOrderFile) throws ServiceException {
        return this.dpcoiWoOrderFileDao.insertDpcoiWoOrderFile(dpcoiWoOrderFile);
    }

    @Override
    public Integer updateDpcoiWoOrderFile(DpcoiWoOrderFile dpcoiWoOrderFile) throws ServiceException {
        return this.dpcoiWoOrderFileDao.updateDpcoiWoOrderFile(dpcoiWoOrderFile);
    }

    @Override
    public List<Map<String, Object>> queryDpcoiWoOrderFileList(DpcoiWoOrderFileQuery dpcoiWoOrderFileQuery) throws ServiceException {
        return this.dpcoiWoOrderFileDao.selectDpcoiWoOrderFileList(dpcoiWoOrderFileQuery);
    }

    @Override
    public List<Map<String, Object>> queryDpcoiOrderFileList(DpcoiWoOrderFileQuery dpcoiWoOrderFileQuery) throws ServiceException {
        return this.dpcoiWoOrderFileDao.selectDpcoiOrderFileList(dpcoiWoOrderFileQuery);
    }

    @Override
    public void editWoOrderUploadFile(Integer dpcoiWoOrderFileId, MultipartFile file, String path, User user) throws ServiceException, Exception {
        String fileName = file.getOriginalFilename();
        String fileType = file.getContentType();
        int index = fileName.lastIndexOf(".");
        String fileSuffix = fileName.substring(index);
        String fileAlias = UUID.randomUUID().toString() + fileSuffix;
        String filePath = path + "fileupload/" + fileAlias;
        file.transferTo(new File(filePath));

        //Excel转PDF
        String excelPdfName = "";
        if("application/vnd.ms-excel".equals(fileType) || "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet".equals(fileType)){
            excelPdfName = UUID.randomUUID().toString() + ".pdf";
            TransferToolUtil.els2pdf(filePath, path + "fileupload/"+excelPdfName);
        }

        FileUpload fileUpload = new FileUpload();
        fileUpload.setFileName(fileName);
        fileUpload.setFileAlias(fileAlias);
        fileUpload.setFileType(fileType);
        fileUpload.setExcelPdfName(excelPdfName);
        fileUpload.setCreateDate(new Date());
        fileUpload.setCreateBy(user.getUserId());
        this.fileUploadDao.insertFileUpload(fileUpload);

        DpcoiWoOrderFile dpcoiWoOrderFile = new DpcoiWoOrderFile();
        dpcoiWoOrderFile.setDpcoiWoOrderId(dpcoiWoOrderFileId);
        dpcoiWoOrderFile.setFileId(fileUpload.getFileId());
        dpcoiWoOrderFile.setCreateDate(new Date());
        dpcoiWoOrderFile.setCreateBy(user.getUserId());
        dpcoiWoOrderFile.setUpdateBy(user.getUserId());
        dpcoiWoOrderFile.setUpdateDate(new Date());
        dpcoiWoOrderFile.setDelFlag("0");
        dpcoiWoOrderFile.setWoFileState(0);
        this.addDpcoiWoOrderFile(dpcoiWoOrderFile);
    }

    @Override
    public List<Map<String, Object>> queryDpcoiWoOrderFileListPage(DpcoiWoOrderFileQuery dpcoiWoOrderFileQuery) throws ServiceException {
        return this.dpcoiWoOrderFileDao.selectDpcoiWoOrderFileListPage(dpcoiWoOrderFileQuery);
    }

    @Override
    public Integer queryDpcoiWoOrderFileCount(DpcoiWoOrderFileQuery dpcoiWoOrderFileQuery) throws ServiceException {
        return this.dpcoiWoOrderFileDao.selectDpcoiWoOrderFileCount(dpcoiWoOrderFileQuery);
    }

}
