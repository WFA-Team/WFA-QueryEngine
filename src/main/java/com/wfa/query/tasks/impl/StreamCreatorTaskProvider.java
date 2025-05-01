package com.wfa.query.tasks.impl;

import java.util.List;

import com.wfa.middleware.taskexecutor.api.ITaskElement;
import com.wfa.middleware.utils.JoinVoid;
import com.wfa.query.tasks.api.IStreamCreatorTaskProvider;

public class StreamCreatorTaskProvider implements IStreamCreatorTaskProvider {

	@Override
	public ITaskElement<JoinVoid> getTask(String streamName, String topic, List<String> fieldNames) {
		// TODO Auto-generated method stub
		return null;
	}

}
