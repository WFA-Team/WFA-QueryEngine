package com.wfa.query.tasks.api;

import java.util.List;

import com.wfa.middleware.taskexecutor.api.ITaskElement;
import com.wfa.middleware.taskexecutor.api.TaskProvider;
import com.wfa.middleware.utils.JoinVoid;

@TaskProvider
public interface IStreamCreatorTaskProvider {
	ITaskElement<JoinVoid> getTask(String streamName, String topic, List<String> fieldNames);
}
