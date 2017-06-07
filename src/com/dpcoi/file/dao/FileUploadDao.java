package com.dpcoi.file.dao;

import com.dpcoi.file.domain.FileUpload;
import com.success.web.framework.mybatis.BaseDao;
import org.springframework.stereotype.Repository;

/**
 * Created by 梁志福 on 2017/4/20.
 */
@Repository
public class FileUploadDao extends BaseDao {
    /**
     * 插入一个FileUpload文件
     * @param fileUpload 文件数据
     * @return 1成功，0不成功
     */
    public Integer insertFileUpload(FileUpload fileUpload){
        return this.sqlSession.insert("fileUpload.insertSelective", fileUpload);
    }

    /**
     * 根据文件Id查询文件实体
     * @param fileUpload 文件ID
     * @return 返回结果
     */
    public FileUpload selectBySelf(FileUpload fileUpload){
        return this.sqlSession.selectOne("fileUpload.selectBySelf", fileUpload);
    }

}
