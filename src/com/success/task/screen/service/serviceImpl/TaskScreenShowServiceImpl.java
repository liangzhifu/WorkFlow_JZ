package com.success.task.screen.service.serviceImpl;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.success.task.derived.service.ExportExcel;
import com.success.task.screen.dao.TaskScreenShowDao;
import com.success.task.screen.domain.TaskScreen;
import com.success.task.screen.query.ScreenQuery;
import com.success.task.screen.service.TaskScreenShowService;
import com.success.web.framework.exception.ServiceException;
import com.success.web.framework.mybatis.Page;

@Service("screenShowService")
public class TaskScreenShowServiceImpl implements TaskScreenShowService {

	@Resource(name = "screenShowDao")
	private TaskScreenShowDao screenShowDao;
	
	@Resource(name = "exportExcel")
	private ExportExcel exportExcel;
	
	@Override
	public Page queryPage(int pageIndex, int pageSize) throws ServiceException {
		// TODO Auto-generated method stub
		return this.screenShowDao.selectPage(pageIndex, pageSize);
	}

	@Override
	public Long queryPageCount() throws ServiceException {
		// TODO Auto-generated method stub
		return this.screenShowDao.selectPageCount();
	}
	
	@Override
	public Long queryOverTimeCount() throws ServiceException {
		// TODO Auto-generated method stub
		return this.screenShowDao.selectOverTimeCount();
	}

	@Override
	public List<TaskScreen> getTaskScreenList(ScreenQuery query) throws ServiceException{
		// TODO Auto-generated method stub
		return this.screenShowDao.selectTaskScreen(query);
	}

	@Override
	public ScreenQuery setScreenQueryData(HttpServletRequest request) {
		// TODO Auto-generated method stub
		ScreenQuery query = new ScreenQuery();
		String orderContractStateCode = request.getParameter("orderContractStateCode");
		String publishCode = request.getParameter("publishCode");
		String changeTimeBeginStr = request.getParameter("changeTimeBegin");
		String changeTimeEndStr = request.getParameter("changeTimeEnd");
		String issueDate = request.getParameter("issueDate");
		String changeContent = request.getParameter("changeContent");
		String productionLine = request.getParameter("productionLine");
		String carType = request.getParameter("carType");
		String mountingMat = request.getParameter("mountingMat");
		
		if(orderContractStateCode != null && !"".equals(orderContractStateCode)){
			query.setOrderContractStateCode(orderContractStateCode);
		}
		
		if(publishCode != null && !"".equals(publishCode)){
			query.setPublishCode(publishCode);
		}
		
		if(changeTimeBeginStr != null && !"".equals(changeTimeBeginStr)){
			query.setChangeTimeBegin(changeTimeBeginStr);
		}
		
		if(changeTimeEndStr != null && !"".equals(changeTimeEndStr)){
			query.setChangeTimeEnd(changeTimeEndStr);
		}
		
		if(issueDate != null && !"".equals(issueDate)){
			query.setIssueDate(issueDate);
		}
		
		if(changeContent != null && !"".equals(changeContent)){
			query.setChangeContent(changeContent);
		}
		
		if(productionLine != null && !"".equals(productionLine)){
			query.setProductionLine(productionLine);
		}
		
		if(carType != null && !"".equals(carType)){
			query.setCarType(carType);
		}
		
		if(mountingMat != null && !"".equals(mountingMat)){
			query.setMountingMat(mountingMat);
		}
		
		return query;
	}

	@Override
	public String doExportExcle(ScreenQuery query, String path)
			throws Exception {
		// TODO Auto-generated method stub
		List<TaskScreen> taskScreenList = this.screenShowDao.selectTaskScreen(query);
		String fileName = this.exportExcel.excelScreenService(taskScreenList, path);
		return fileName;
	}

}
