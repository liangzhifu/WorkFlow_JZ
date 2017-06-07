package com.success.templet.task.service.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.springframework.stereotype.Service;

import com.success.common.info.domain.InfoValue;
import com.success.templet.content.dao.TaskTacheInfoDao;
import com.success.templet.content.dao.TaskTypeInfoDao;
import com.success.templet.content.domain.TaskTacheInfo;
import com.success.templet.content.domain.TaskTacheInfoValue;
import com.success.templet.content.domain.TaskTypeInfo;
import com.success.templet.content.domain.TaskTypeInfoValue;
import com.success.templet.content.query.TaskTacheInfoQuery;
import com.success.templet.content.query.TaskTypeInfoQuery;
import com.success.templet.run.dao.TaskTypeRunDao;
import com.success.templet.run.domain.TaskTypeRun;
import com.success.templet.run.query.TaskTypeRunQuery;
import com.success.templet.tache.domain.TaskTache;
import com.success.templet.task.dao.TaskTypeDao;
import com.success.templet.task.domain.TaskType;
import com.success.templet.task.query.TaskTypeQuery;
import com.success.templet.task.service.TaskTypeService;
import com.success.web.framework.exception.ServiceException;
import com.success.web.framework.mybatis.Page;

@Service("taskTypeService")
public class TaskTypeServiceImpl implements TaskTypeService {

	@Resource(name = "taskTypeDao")
	private TaskTypeDao taskTypeDao;
	
	@Resource(name = "taskTacheInfoDao")
	private TaskTacheInfoDao taskTacheInfoDao;
	
	@Resource(name = "taskTypeInfoDao")
	private TaskTypeInfoDao taskTypeInfoDao;
	
	@Resource(name = "taskTypeRunDao")
	private TaskTypeRunDao taskTypeRunDao;
	
	@Override
	public Page getPageTaskType(TaskTypeQuery query, int pageIndex,
			int pageSize) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TaskType> queryTaskTypes(TaskTypeQuery query) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JSONArray queryTaskTypeList(TaskTypeQuery query)
			throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer addTaskType(TaskType taskType)
			throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer editTaskType(TaskType taskType)
			throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer deleteTaskType(TaskType taskType)
			throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TaskType selectTaskTypeByIdQuery(TaskTypeQuery query)
			throws ServiceException {
		// TODO Auto-generated method stub
		TaskType taskType = new TaskType();
		List<TaskTache> taskTacheList =  new ArrayList<TaskTache>(); 
		List<TaskTypeInfo> taskTypeInfoList =  new ArrayList<TaskTypeInfo>(); 
		
		TaskTypeRunQuery taskTypeRunQuery = new TaskTypeRunQuery();
		taskTypeRunQuery.setTaskTypeId(query.getTaskTypeId());
		List<TaskTypeRun> taskTypeRunList = this.taskTypeRunDao.selectTaskTypeRun(taskTypeRunQuery);
		taskType.setTaskTypeRunList(taskTypeRunList);
		
		TaskTypeInfoQuery taskTypeInfoQuery = new TaskTypeInfoQuery();
		taskTypeInfoQuery.setTaskTypeId(query.getTaskTypeId());
		List<TaskTypeInfoValue> taskTypeInfoValueList = this.taskTypeInfoDao.selectTaskTypeInfoValueByIdQuery(taskTypeInfoQuery);
		int tempTaskTypeInfoId = 0;
		List<InfoValue> infoValueList = new ArrayList<InfoValue>();
		TaskTypeInfo taskTypeInfo = null;
		for(int i = 0; i < taskTypeInfoValueList.size(); i++){
			TaskTypeInfoValue taskTypeInfoValue = taskTypeInfoValueList.get(i);
			int taskTypeInfoId = taskTypeInfoValue.getTaskTypeInfoId();			
			int infoSeq = taskTypeInfoValue.getInfoSeq();			
			String infoCode = taskTypeInfoValue.getInfoCode();			
			String infoName = taskTypeInfoValue.getInfoName();	
			float infoLength = taskTypeInfoValue.getInfoLength();
			int infoTypeId = taskTypeInfoValue.getInfoTypeId();			
			Integer sysInfoId = taskTypeInfoValue.getSysInfoId();			
			String sysInfoName = taskTypeInfoValue.getSysInfoName();			
			Integer infoKey = taskTypeInfoValue.getInfoKey();			
			String infoKeyName = taskTypeInfoValue.getInfoKeyName();
			if(tempTaskTypeInfoId == taskTypeInfoId){
				InfoValue infoValue = new InfoValue();
				infoValue.setInfoId(sysInfoId);
				infoValue.setInfoKey(infoKey);
				infoValue.setInfoName(sysInfoName);
				infoValue.setInfoKeyName(infoKeyName);
				infoValueList.add(infoValue);
			}else {
				if(taskTypeInfo == null){
					
				}else {
					taskTypeInfo.setInfoValueList(infoValueList);
					taskTypeInfoList.add(taskTypeInfo);
				}
				taskTypeInfo = new TaskTypeInfo();
				taskTypeInfo.setTaskTypeId(query.getTaskTypeId());
				taskTypeInfo.setTaskTypeInfoId(taskTypeInfoId);
				taskTypeInfo.setInfoSeq(infoSeq);
				taskTypeInfo.setInfoCode(infoCode);
				taskTypeInfo.setInfoName(infoName);
				taskTypeInfo.setInfoTypeId(infoTypeId);
				taskTypeInfo.setInfoLength(infoLength);
				
				infoValueList = new ArrayList<InfoValue>();
				InfoValue infoValue = new InfoValue();
				infoValue.setInfoId(sysInfoId);
				infoValue.setInfoKey(infoKey);
				infoValue.setInfoName(sysInfoName);
				infoValue.setInfoKeyName(infoKeyName);
				infoValueList.add(infoValue);
				tempTaskTypeInfoId = taskTypeInfoId;
			}
		}
		if(taskTypeInfo != null){
			taskTypeInfo.setInfoValueList(infoValueList);
			taskTypeInfoList.add(taskTypeInfo);
		}
		
		TaskTacheInfoQuery taskTacheInfoQuery = new TaskTacheInfoQuery();
		taskTacheInfoQuery.setTaskTypeId(query.getTaskTypeId());
		List<TaskTacheInfoValue> taskTacheInfoValueList = this.taskTacheInfoDao.selectTaskTacheInfoValueByIdQuery(taskTacheInfoQuery);
		int tempTacheId = 0;
		int tempTaskTacheInfoId = 0;
		TaskTache taskTache = null;
		TaskTacheInfo taskTacheInfo = null;
		infoValueList = new ArrayList<InfoValue>();
		List<TaskTacheInfo> taskTacheInfoList = new ArrayList<TaskTacheInfo>();
		for(int i = 0; i < taskTacheInfoValueList.size(); i++){
			TaskTacheInfoValue taskTacheInfoValue = taskTacheInfoValueList.get(i);
			int tacheId = taskTacheInfoValue.getTacheId();			
			String tacheCode = taskTacheInfoValue.getTacheCode();			
			String tacheName = taskTacheInfoValue.getTacheName();			
			int orgId = taskTacheInfoValue.getOrgId();			
			String orgName = taskTacheInfoValue.getOrgName();			
			Integer staffId = taskTacheInfoValue.getStaffId();
			String staffName = taskTacheInfoValue.getStaffName();
			int taskTacheInfoId = taskTacheInfoValue.getTaskTacheInfoId();		
			int infoSeq = taskTacheInfoValue.getInfoSeq();			
			String infoCode = taskTacheInfoValue.getInfoCode();			
			String infoName = taskTacheInfoValue.getInfoName();			
			int infoTypeId = taskTacheInfoValue.getInfoTypeId();			
			int sysInfoId = taskTacheInfoValue.getSysInfoId();			
			String sysInfoName = taskTacheInfoValue.getSysInfoName();			
			Integer infoKey = taskTacheInfoValue.getInfoKey();			
			String infoKeyName = taskTacheInfoValue.getInfoKeyName();
			if(tempTacheId == tacheId){
				if(tempTaskTacheInfoId == taskTacheInfoId){
					InfoValue infoValue = new InfoValue();
					infoValue.setInfoId(sysInfoId);
					infoValue.setInfoKey(infoKey);
					infoValue.setInfoName(sysInfoName);
					infoValue.setInfoKeyName(infoKeyName);
					infoValueList.add(infoValue);
				}else {
					taskTacheInfo.setInfoValueList(infoValueList);
					taskTacheInfoList.add(taskTacheInfo);
					
					infoValueList = new ArrayList<InfoValue>();
					InfoValue infoValue = new InfoValue();
					infoValue.setInfoId(sysInfoId);
					infoValue.setInfoKey(infoKey);
					infoValue.setInfoName(sysInfoName);
					infoValue.setInfoKeyName(infoKeyName);
					infoValueList.add(infoValue);
				}
			}else {
				if(taskTache == null){
					
				}else {
					taskTacheInfo.setInfoValueList(infoValueList);
					taskTacheInfoList.add(taskTacheInfo);
					taskTache.setTaskTacheInfo(taskTacheInfoList);
					taskTacheList.add(taskTache);
				}
				
				infoValueList = new ArrayList<InfoValue>();
				InfoValue infoValue = new InfoValue();
				infoValue.setInfoId(sysInfoId);
				infoValue.setInfoKey(infoKey);
				infoValue.setInfoName(sysInfoName);
				infoValue.setInfoKeyName(infoKeyName);
				infoValueList.add(infoValue);
				
				taskTacheInfoList = new ArrayList<TaskTacheInfo>();
				taskTacheInfo = new TaskTacheInfo();
				taskTacheInfo.setTacheId(tacheId);
				taskTacheInfo.setTaskTacheInfoId(taskTacheInfoId);
				taskTacheInfo.setInfoSeq(infoSeq);
				taskTacheInfo.setInfoCode(infoCode);
				taskTacheInfo.setInfoName(infoName);
				taskTacheInfo.setInfoTypeId(infoTypeId);
				
				taskTache = new TaskTache();
				taskTache.setTaskTypeId(query.getTaskTypeId());
				taskTache.setTacheId(tacheId);
				taskTache.setTacheCode(tacheCode);
				taskTache.setTacheName(tacheName);
				taskTache.setOrgId(orgId);
				taskTache.setOrgName(orgName);
				taskTache.setStaffId(staffId);
				taskTache.setStaffName(staffName);
				
				tempTacheId = tacheId;
				tempTaskTacheInfoId = taskTacheInfoId;
			}
		}
		if(taskTache != null){
			taskTacheInfo.setInfoValueList(infoValueList);
			taskTacheInfoList.add(taskTacheInfo);
			taskTache.setTaskTacheInfo(taskTacheInfoList);
			taskTacheList.add(taskTache);
		}
		
		TaskType taskTypeSelf = this.taskTypeDao.selectTaskTypeByIdQuery(query);
		taskType.setTaskTypeId(query.getTaskTypeId());
		taskType.setTaskTypeName(taskTypeSelf.getTaskTypeName());
		taskType.setTaskTypeWordPath(taskTypeSelf.getTaskTypeWordPath());
		taskType.setCycleId(taskTypeSelf.getCycleId());
		taskType.setVersion(taskTypeSelf.getVersion());
		taskType.setTaskTache(taskTacheList);
		taskType.setTaskTypeInfo(taskTypeInfoList);
		return taskType;
	}

	@Override
	public TaskTypeQuery setTaskTypeQueryData(HttpServletRequest request) {
		// TODO Auto-generated method stub
		TaskTypeQuery query = new TaskTypeQuery();
		String taskTypeId = request.getParameter("taskTypeId");
		String taskTypeName = request.getParameter("taskTypeName");
		
		if(taskTypeId != null && !taskTypeId.equals("")){
			query.setTaskTypeId(Integer.parseInt(taskTypeId));
		}
		
		if(taskTypeName != null){
			query.setTaskTypeName(taskTypeName);
		}

		return query;
	}

	@Override
	public TaskType selectTaskTypeSelf(TaskTypeQuery query) throws ServiceException {
		// TODO Auto-generated method stub
		return this.taskTypeDao.selectTaskTypeByIdQuery(query);
	}

	@Override
	public TaskType selectTaskTypeByIdQueryOld(TaskTypeQuery query)
			throws ServiceException {
		// TODO Auto-generated method stub
		TaskType taskType = new TaskType();
		List<TaskTache> taskTacheList =  new ArrayList<TaskTache>(); 
		List<TaskTypeInfo> taskTypeInfoList =  new ArrayList<TaskTypeInfo>(); 
		
		TaskTypeRunQuery taskTypeRunQuery = new TaskTypeRunQuery();
		taskTypeRunQuery.setTaskTypeId(query.getTaskTypeId());
		List<TaskTypeRun> taskTypeRunList = this.taskTypeRunDao.selectTaskTypeRun(taskTypeRunQuery);
		taskType.setTaskTypeRunList(taskTypeRunList);
		
		TaskTypeInfoQuery taskTypeInfoQuery = new TaskTypeInfoQuery();
		taskTypeInfoQuery.setTaskTypeId(query.getTaskTypeId());
		List<TaskTypeInfoValue> taskTypeInfoValueList = this.taskTypeInfoDao.selectTaskTypeInfoValueByIdQuery(taskTypeInfoQuery);
		int tempTaskTypeInfoId = 0;
		List<InfoValue> infoValueList = new ArrayList<InfoValue>();
		TaskTypeInfo taskTypeInfo = null;
		for(int i = 0; i < taskTypeInfoValueList.size(); i++){
			TaskTypeInfoValue taskTypeInfoValue = taskTypeInfoValueList.get(i);
			int taskTypeInfoId = taskTypeInfoValue.getTaskTypeInfoId();			
			int infoSeq = taskTypeInfoValue.getInfoSeq();			
			String infoCode = taskTypeInfoValue.getInfoCode();			
			String infoName = taskTypeInfoValue.getInfoName();	
			float infoLength = taskTypeInfoValue.getInfoLength();
			int infoTypeId = taskTypeInfoValue.getInfoTypeId();			
			Integer sysInfoId = taskTypeInfoValue.getSysInfoId();			
			String sysInfoName = taskTypeInfoValue.getSysInfoName();			
			Integer infoKey = taskTypeInfoValue.getInfoKey();			
			String infoKeyName = taskTypeInfoValue.getInfoKeyName();
			if(tempTaskTypeInfoId == taskTypeInfoId){
				InfoValue infoValue = new InfoValue();
				infoValue.setInfoId(sysInfoId);
				infoValue.setInfoKey(infoKey);
				infoValue.setInfoName(sysInfoName);
				infoValue.setInfoKeyName(infoKeyName);
				infoValueList.add(infoValue);
			}else {
				if(taskTypeInfo == null){
					
				}else {
					taskTypeInfo.setInfoValueList(infoValueList);
					taskTypeInfoList.add(taskTypeInfo);
				}
				taskTypeInfo = new TaskTypeInfo();
				taskTypeInfo.setTaskTypeId(query.getTaskTypeId());
				taskTypeInfo.setTaskTypeInfoId(taskTypeInfoId);
				taskTypeInfo.setInfoSeq(infoSeq);
				taskTypeInfo.setInfoCode(infoCode);
				taskTypeInfo.setInfoName(infoName);
				taskTypeInfo.setInfoTypeId(infoTypeId);
				taskTypeInfo.setInfoLength(infoLength);
				
				infoValueList = new ArrayList<InfoValue>();
				InfoValue infoValue = new InfoValue();
				infoValue.setInfoId(sysInfoId);
				infoValue.setInfoKey(infoKey);
				infoValue.setInfoName(sysInfoName);
				infoValue.setInfoKeyName(infoKeyName);
				infoValueList.add(infoValue);
				tempTaskTypeInfoId = taskTypeInfoId;
			}
		}
		if(taskTypeInfo != null){
			taskTypeInfo.setInfoValueList(infoValueList);
			taskTypeInfoList.add(taskTypeInfo);
		}
		
		TaskTacheInfoQuery taskTacheInfoQuery = new TaskTacheInfoQuery();
		taskTacheInfoQuery.setTaskTypeId(query.getTaskTypeId());
		List<TaskTacheInfoValue> taskTacheInfoValueList = this.taskTacheInfoDao.selectTaskTacheInfoValueByIdQueryOld(taskTacheInfoQuery);
		int tempTacheId = 0;
		int tempTaskTacheInfoId = 0;
		TaskTache taskTache = null;
		TaskTacheInfo taskTacheInfo = null;
		infoValueList = new ArrayList<InfoValue>();
		List<TaskTacheInfo> taskTacheInfoList = new ArrayList<TaskTacheInfo>();
		for(int i = 0; i < taskTacheInfoValueList.size(); i++){
			TaskTacheInfoValue taskTacheInfoValue = taskTacheInfoValueList.get(i);
			int tacheId = taskTacheInfoValue.getTacheId();			
			String tacheCode = taskTacheInfoValue.getTacheCode();			
			String tacheName = taskTacheInfoValue.getTacheName();			
			int orgId = taskTacheInfoValue.getOrgId();			
			String orgName = taskTacheInfoValue.getOrgName();			
			Integer staffId = taskTacheInfoValue.getStaffId();
			String staffName = taskTacheInfoValue.getStaffName();
			int taskTacheInfoId = taskTacheInfoValue.getTaskTacheInfoId();		
			int infoSeq = taskTacheInfoValue.getInfoSeq();			
			String infoCode = taskTacheInfoValue.getInfoCode();			
			String infoName = taskTacheInfoValue.getInfoName();			
			int infoTypeId = taskTacheInfoValue.getInfoTypeId();			
			int sysInfoId = taskTacheInfoValue.getSysInfoId();			
			String sysInfoName = taskTacheInfoValue.getSysInfoName();			
			Integer infoKey = taskTacheInfoValue.getInfoKey();			
			String infoKeyName = taskTacheInfoValue.getInfoKeyName();
			if(tempTacheId == tacheId){
				if(tempTaskTacheInfoId == taskTacheInfoId){
					InfoValue infoValue = new InfoValue();
					infoValue.setInfoId(sysInfoId);
					infoValue.setInfoKey(infoKey);
					infoValue.setInfoName(sysInfoName);
					infoValue.setInfoKeyName(infoKeyName);
					infoValueList.add(infoValue);
				}else {
					taskTacheInfo.setInfoValueList(infoValueList);
					taskTacheInfoList.add(taskTacheInfo);
					
					infoValueList = new ArrayList<InfoValue>();
					InfoValue infoValue = new InfoValue();
					infoValue.setInfoId(sysInfoId);
					infoValue.setInfoKey(infoKey);
					infoValue.setInfoName(sysInfoName);
					infoValue.setInfoKeyName(infoKeyName);
					infoValueList.add(infoValue);
				}
			}else {
				if(taskTache == null){
					
				}else {
					taskTacheInfo.setInfoValueList(infoValueList);
					taskTacheInfoList.add(taskTacheInfo);
					taskTache.setTaskTacheInfo(taskTacheInfoList);
					taskTacheList.add(taskTache);
				}
				
				infoValueList = new ArrayList<InfoValue>();
				InfoValue infoValue = new InfoValue();
				infoValue.setInfoId(sysInfoId);
				infoValue.setInfoKey(infoKey);
				infoValue.setInfoName(sysInfoName);
				infoValue.setInfoKeyName(infoKeyName);
				infoValueList.add(infoValue);
				
				taskTacheInfoList = new ArrayList<TaskTacheInfo>();
				taskTacheInfo = new TaskTacheInfo();
				taskTacheInfo.setTacheId(tacheId);
				taskTacheInfo.setTaskTacheInfoId(taskTacheInfoId);
				taskTacheInfo.setInfoSeq(infoSeq);
				taskTacheInfo.setInfoCode(infoCode);
				taskTacheInfo.setInfoName(infoName);
				taskTacheInfo.setInfoTypeId(infoTypeId);
				
				taskTache = new TaskTache();
				taskTache.setTaskTypeId(query.getTaskTypeId());
				taskTache.setTacheId(tacheId);
				taskTache.setTacheCode(tacheCode);
				taskTache.setTacheName(tacheName);
				taskTache.setOrgId(orgId);
				taskTache.setOrgName(orgName);
				taskTache.setStaffId(staffId);
				taskTache.setStaffName(staffName);
				
				tempTacheId = tacheId;
				tempTaskTacheInfoId = taskTacheInfoId;
			}
		}
		if(taskTache != null){
			taskTacheInfo.setInfoValueList(infoValueList);
			taskTacheInfoList.add(taskTacheInfo);
			taskTache.setTaskTacheInfo(taskTacheInfoList);
			taskTacheList.add(taskTache);
		}
		
		TaskType taskTypeSelf = this.taskTypeDao.selectTaskTypeByIdQuery(query);
		taskType.setTaskTypeId(query.getTaskTypeId());
		taskType.setTaskTypeName(taskTypeSelf.getTaskTypeName());
		taskType.setTaskTypeWordPath(taskTypeSelf.getTaskTypeWordPath());
		taskType.setCycleId(taskTypeSelf.getCycleId());
		taskType.setTaskTache(taskTacheList);
		taskType.setTaskTypeInfo(taskTypeInfoList);
		return taskType;
	}

	@Override
	public TaskType selectTaskTypeByIdQueryVersion(TaskTypeQuery query) throws ServiceException {
		// TODO Auto-generated method stub
		TaskType taskType = new TaskType();
		List<TaskTache> taskTacheList =  new ArrayList<TaskTache>(); 
		List<TaskTypeInfo> taskTypeInfoList =  new ArrayList<TaskTypeInfo>(); 
		
		TaskTypeRunQuery taskTypeRunQuery = new TaskTypeRunQuery();
		taskTypeRunQuery.setTaskTypeId(query.getTaskTypeId());
		List<TaskTypeRun> taskTypeRunList = this.taskTypeRunDao.selectTaskTypeRun(taskTypeRunQuery);
		taskType.setTaskTypeRunList(taskTypeRunList);
		
		TaskTypeInfoQuery taskTypeInfoQuery = new TaskTypeInfoQuery();
		taskTypeInfoQuery.setTaskTypeId(query.getTaskTypeId());
		List<TaskTypeInfoValue> taskTypeInfoValueList = this.taskTypeInfoDao.selectTaskTypeInfoValueByIdQuery(taskTypeInfoQuery);
		int tempTaskTypeInfoId = 0;
		List<InfoValue> infoValueList = new ArrayList<InfoValue>();
		TaskTypeInfo taskTypeInfo = null;
		for(int i = 0; i < taskTypeInfoValueList.size(); i++){
			TaskTypeInfoValue taskTypeInfoValue = taskTypeInfoValueList.get(i);
			int taskTypeInfoId = taskTypeInfoValue.getTaskTypeInfoId();			
			int infoSeq = taskTypeInfoValue.getInfoSeq();			
			String infoCode = taskTypeInfoValue.getInfoCode();			
			String infoName = taskTypeInfoValue.getInfoName();	
			float infoLength = taskTypeInfoValue.getInfoLength();
			int infoTypeId = taskTypeInfoValue.getInfoTypeId();			
			Integer sysInfoId = taskTypeInfoValue.getSysInfoId();			
			String sysInfoName = taskTypeInfoValue.getSysInfoName();			
			Integer infoKey = taskTypeInfoValue.getInfoKey();			
			String infoKeyName = taskTypeInfoValue.getInfoKeyName();
			Integer agreementFlag = taskTypeInfoValue.getAgreementFlag();
			if(tempTaskTypeInfoId == taskTypeInfoId){
				InfoValue infoValue = new InfoValue();
				infoValue.setInfoId(sysInfoId);
				infoValue.setInfoKey(infoKey);
				infoValue.setInfoName(sysInfoName);
				infoValue.setInfoKeyName(infoKeyName);
				infoValue.setAgreementFlag(agreementFlag);
				infoValueList.add(infoValue);
			}else {
				if(taskTypeInfo == null){
					
				}else {
					taskTypeInfo.setInfoValueList(infoValueList);
					taskTypeInfoList.add(taskTypeInfo);
				}
				taskTypeInfo = new TaskTypeInfo();
				taskTypeInfo.setTaskTypeId(query.getTaskTypeId());
				taskTypeInfo.setTaskTypeInfoId(taskTypeInfoId);
				taskTypeInfo.setInfoSeq(infoSeq);
				taskTypeInfo.setInfoCode(infoCode);
				taskTypeInfo.setInfoName(infoName);
				taskTypeInfo.setInfoTypeId(infoTypeId);
				taskTypeInfo.setInfoLength(infoLength);
				
				infoValueList = new ArrayList<InfoValue>();
				InfoValue infoValue = new InfoValue();
				infoValue.setInfoId(sysInfoId);
				infoValue.setInfoKey(infoKey);
				infoValue.setInfoName(sysInfoName);
				infoValue.setInfoKeyName(infoKeyName);
				infoValue.setAgreementFlag(agreementFlag);
				infoValueList.add(infoValue);
				tempTaskTypeInfoId = taskTypeInfoId;
			}
		}
		if(taskTypeInfo != null){
			taskTypeInfo.setInfoValueList(infoValueList);
			taskTypeInfoList.add(taskTypeInfo);
		}
		
		TaskTacheInfoQuery taskTacheInfoQuery = new TaskTacheInfoQuery();
		taskTacheInfoQuery.setTaskTypeId(query.getTaskTypeId());
		taskTacheInfoQuery.setVersion(query.getVersion());
		List<TaskTacheInfoValue> taskTacheInfoValueList = this.taskTacheInfoDao.selectTaskTacheInfoValueByIdQueryVersion(taskTacheInfoQuery);
		int tempTacheId = 0;
		int tempTaskTacheInfoId = 0;
		TaskTache taskTache = null;
		TaskTacheInfo taskTacheInfo = null;
		infoValueList = new ArrayList<InfoValue>();
		List<TaskTacheInfo> taskTacheInfoList = new ArrayList<TaskTacheInfo>();
		for(int i = 0; i < taskTacheInfoValueList.size(); i++){
			TaskTacheInfoValue taskTacheInfoValue = taskTacheInfoValueList.get(i);
			int tacheId = taskTacheInfoValue.getTacheId();			
			String tacheCode = taskTacheInfoValue.getTacheCode();			
			String tacheName = taskTacheInfoValue.getTacheName();			
			int orgId = taskTacheInfoValue.getOrgId();			
			String orgName = taskTacheInfoValue.getOrgName();			
			Integer staffId = taskTacheInfoValue.getStaffId();
			String staffName = taskTacheInfoValue.getStaffName();
			int taskTacheInfoId = taskTacheInfoValue.getTaskTacheInfoId();		
			int infoSeq = taskTacheInfoValue.getInfoSeq();			
			String infoCode = taskTacheInfoValue.getInfoCode();			
			String infoName = taskTacheInfoValue.getInfoName();			
			int infoTypeId = taskTacheInfoValue.getInfoTypeId();			
			int sysInfoId = taskTacheInfoValue.getSysInfoId();			
			String sysInfoName = taskTacheInfoValue.getSysInfoName();			
			Integer infoKey = taskTacheInfoValue.getInfoKey();			
			String infoKeyName = taskTacheInfoValue.getInfoKeyName();
			Integer agreementFlag = taskTacheInfoValue.getAgreementFlag();
			if(tempTacheId == tacheId){
				if(tempTaskTacheInfoId == taskTacheInfoId){
					InfoValue infoValue = new InfoValue();
					infoValue.setInfoId(sysInfoId);
					infoValue.setInfoKey(infoKey);
					infoValue.setInfoName(sysInfoName);
					infoValue.setInfoKeyName(infoKeyName);
					infoValue.setAgreementFlag(agreementFlag);
					infoValueList.add(infoValue);
				}else {
					taskTacheInfo.setInfoValueList(infoValueList);
					taskTacheInfoList.add(taskTacheInfo);
					
					infoValueList = new ArrayList<InfoValue>();
					InfoValue infoValue = new InfoValue();
					infoValue.setInfoId(sysInfoId);
					infoValue.setInfoKey(infoKey);
					infoValue.setInfoName(sysInfoName);
					infoValue.setInfoKeyName(infoKeyName);
					infoValue.setAgreementFlag(agreementFlag);
					infoValueList.add(infoValue);
				}
			}else {
				if(taskTache == null){
					
				}else {
					taskTacheInfo.setInfoValueList(infoValueList);
					taskTacheInfoList.add(taskTacheInfo);
					taskTache.setTaskTacheInfo(taskTacheInfoList);
					taskTacheList.add(taskTache);
				}
				
				infoValueList = new ArrayList<InfoValue>();
				InfoValue infoValue = new InfoValue();
				infoValue.setInfoId(sysInfoId);
				infoValue.setInfoKey(infoKey);
				infoValue.setInfoName(sysInfoName);
				infoValue.setInfoKeyName(infoKeyName);
				infoValue.setAgreementFlag(agreementFlag);
				infoValueList.add(infoValue);
				
				taskTacheInfoList = new ArrayList<TaskTacheInfo>();
				taskTacheInfo = new TaskTacheInfo();
				taskTacheInfo.setTacheId(tacheId);
				taskTacheInfo.setTaskTacheInfoId(taskTacheInfoId);
				taskTacheInfo.setInfoSeq(infoSeq);
				taskTacheInfo.setInfoCode(infoCode);
				taskTacheInfo.setInfoName(infoName);
				taskTacheInfo.setInfoTypeId(infoTypeId);
				
				taskTache = new TaskTache();
				taskTache.setTaskTypeId(query.getTaskTypeId());
				taskTache.setTacheId(tacheId);
				taskTache.setTacheCode(tacheCode);
				taskTache.setTacheName(tacheName);
				taskTache.setOrgId(orgId);
				taskTache.setOrgName(orgName);
				taskTache.setStaffId(staffId);
				taskTache.setStaffName(staffName);
				
				tempTacheId = tacheId;
				tempTaskTacheInfoId = taskTacheInfoId;
			}
		}
		if(taskTache != null){
			taskTacheInfo.setInfoValueList(infoValueList);
			taskTacheInfoList.add(taskTacheInfo);
			taskTache.setTaskTacheInfo(taskTacheInfoList);
			taskTacheList.add(taskTache);
		}
		
		TaskType taskTypeSelf = this.taskTypeDao.selectTaskTypeByIdQuery(query);
		taskType.setTaskTypeId(query.getTaskTypeId());
		taskType.setTaskTypeName(taskTypeSelf.getTaskTypeName());
		taskType.setTaskTypeWordPath(taskTypeSelf.getTaskTypeWordPath());
		taskType.setCycleId(taskTypeSelf.getCycleId());
		taskType.setTaskTache(taskTacheList);
		taskType.setTaskTypeInfo(taskTypeInfoList);
		return taskType;
	}

}
