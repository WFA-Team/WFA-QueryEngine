package com.wfa.query.tasks.api;

import com.wfa.middleware.taskexecutor.api.ITaskElement;
import com.wfa.middleware.taskexecutor.api.TaskProvider;
import com.wfa.middleware.utils.JoinVoid;

@TaskProvider
public interface IShutdownSequenceTaskProvider {
	ITaskElement<JoinVoid> getTask();
}
