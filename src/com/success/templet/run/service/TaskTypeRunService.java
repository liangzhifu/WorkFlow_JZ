package com.success.templet.run.service;

import java.util.List;

import com.success.templet.run.domain.TaskTypeRun;
import com.success.templet.run.query.TaskTypeRunQuery;

public interface TaskTypeRunService {

	public List<TaskTypeRun> queryTaskTypeRuns(TaskTypeRunQuery query);
	
}
