package com.dpcoi.file.controller;/**
 * Created by liangzhifu
 * DATE:2017/5/5.
 */

import com.dpcoi.file.domain.FileUpload;
import com.dpcoi.file.service.FileUploadService;
import com.success.common.Constant;
import com.success.sys.user.domain.User;
import com.success.task.detail.domain.TaskOrder;
import com.success.task.detail.service.TaskOrderService;
import com.success.web.framework.util.AjaxUtil;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 *
 * @author lzf
 * @create 2017-05-05
 **/
@Controller
@RequestMapping("/fileUpload")
public class FileUploadController implements ServletContextAware {

    @Resource(name = "fileUploadService")
    private FileUploadService fileUploadService;

    @Resource(name = "taskOrderService")
    private TaskOrderService taskOrderService;

    //Spring这里是通过实现ServletContextAware接口来注入ServletContext对象
    private ServletContext servletContext;

    @RequestMapping(value="download.do",method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public void fileDownload(HttpServletResponse response, Integer fileId)throws Exception{
        FileUpload fileUpload = new FileUpload();
        fileUpload.setFileId(fileId);
        fileUpload = this.fileUploadService.queryFileUpload(fileUpload);
        //获取网站部署路径(通过ServletContext对象)，用于确定下载文件位置，从而实现下载
        String path = servletContext.getRealPath("/");

        //1.设置文件ContentType类型，这样设置，会自动判断下载文件类型
        response.setContentType("multipart/form-data");

        String fileName = fileUpload.getFileName();

        //2.设置文件头：最后一个参数是设置下载文件名(假如我们叫a.pdf)
        response.setHeader("Content-Disposition", "attachment;fileName="+new String( fileName.getBytes("gb2312"), "ISO8859-1" ) );
        ServletOutputStream out;
        //通过文件路径获得File对象(假如此路径中有一个download.pdf文件)
        File file = new File(path + "fileupload/" + fileUpload.getFileAlias());

        try {
            FileInputStream inputStream = new FileInputStream(file);

            //3.通过response获取ServletOutputStream对象(out)
            out = response.getOutputStream();

            int b = 0;
            byte[] buffer = new byte[512];
            while (b != -1){
                b = inputStream.read(buffer);
                //4.写到输出流(out)中
                if(b != -1) {
                    out.write(buffer,0,b);
                }
            }
            inputStream.close();
            out.close();
            out.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    @RequestMapping(value="/uploadFile.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public void uploadFile(HttpServletRequest request, HttpServletResponse response, @RequestParam("uploadFile") MultipartFile file){
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            User user = (User)request.getSession().getAttribute(Constant.STAFF_KEY);
            String path = request.getSession().getServletContext().getRealPath("/");
            if (!path.endsWith(java.io.File.separator)) {
                path = path + java.io.File.separator;
            }
            if(!file.isEmpty()){
                FileUpload fileUpload = this.fileUploadService.addUploadFile(file, path, user);
                map.put("success", true);
                map.put("fileId", fileUpload.getFileId());
                map.put("fileName", fileUpload.getFileName());
                map.put("message", "上传成功");
            }else {
                map.put("success", false);
                map.put("message", "上传失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            map.put("success", false);
            map.put("message", e.getMessage());
        }
        AjaxUtil.ajaxResponse(response, new JSONObject(map).toString(), AjaxUtil.RESPONCE_TYPE_JSON);
    }

    @RequestMapping(value="/taskOrderUploadFile.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public void taskOrderUploadFile(HttpServletRequest request, HttpServletResponse response, @RequestParam("taskOrderId") Integer taskOrderId, @RequestParam("uploadFile") MultipartFile file){
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            User user = (User)request.getSession().getAttribute(Constant.STAFF_KEY);
            String path = request.getSession().getServletContext().getRealPath("/");
            if (!path.endsWith(java.io.File.separator)) {
                path = path + java.io.File.separator;
            }
            if(!file.isEmpty()){
                FileUpload fileUpload = this.fileUploadService.addUploadFile(file, path, user);
                TaskOrder taskOrder = new TaskOrder();
                taskOrder.setOrderId(taskOrderId);
                taskOrder.setMaterialFileId(fileUpload.getFileId());
                this.taskOrderService.updateTaskOrder(taskOrder);
                map.put("success", true);
                map.put("fileId", fileUpload.getFileId());
                map.put("fileName", fileUpload.getFileName());
                map.put("message", "上传成功");
            }else {
                map.put("success", false);
                map.put("message", "上传失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            map.put("success", false);
            map.put("message", e.getMessage());
        }
        AjaxUtil.ajaxResponse(response, new JSONObject(map).toString(), AjaxUtil.RESPONCE_TYPE_JSON);
    }
}
