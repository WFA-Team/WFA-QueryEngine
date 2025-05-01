package com.wfa.query.orchestrator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.wfa.middleware.taskexecutor.api.ITaskElement;
import com.wfa.middleware.taskexecutor.api.ITaskExecutorEngine;
import com.wfa.middleware.utils.JoinVoid;
import com.wfa.middleware.utils.PlayType;
import com.wfa.query.api.IQueryEngineOrchestrator;
import com.wfa.query.tasks.api.IQueryEngineTaskProviderRepository;
import com.wfa.query.tasks.api.IShutdownSequenceTaskProvider;

@Component
public class QueryEngineOrchestrator implements IQueryEngineOrchestrator {

	private final ITaskExecutorEngine taskEngine;
	private final IQueryEngineTaskProviderRepository taskRepo;
	
	@Autowired
	public QueryEngineOrchestrator(ITaskExecutorEngine taskEngine, IQueryEngineTaskProviderRepository taskRepo) {
		this.taskEngine = taskEngine;
		this.taskRepo = taskRepo;
	}
	
	@Override
	public void executeQuery(String query) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exit() {
		startTaskEngineIfNotStarted();
		taskEngine.schedule(getShutdownSequenceTask());
	}

	@Override
	public void executeQueryRT(String query) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createStream(String streamName, 
			String topic, List<String> fieldNames) {
		// TODO Auto-generated method stub
		
	}
	
	private ITaskElement<JoinVoid> getShutdownSequenceTask() {
		return taskRepo.<IShutdownSequenceTaskProvider>getTaskProvider(IShutdownSequenceTaskProvider.class).getTask();
	}
	
	private void startTaskEngineIfNotStarted() {
		if (!taskEngine.getState().equals(PlayType.STARTED))
			taskEngine.startEngine();
	}

}
