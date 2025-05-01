package com.wfa.query.tasks.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wfa.crosstalk.ksql.api.QueryManager;
import com.wfa.middleware.taskexecutor.api.ATaskElement;
import com.wfa.middleware.taskexecutor.api.ITaskElement;
import com.wfa.middleware.taskexecutor.api.ITaskExecutorEngine;
import com.wfa.middleware.utils.JoinVoid;
import com.wfa.query.tasks.api.IQueryExecutorTaskProvider;

@Component
public class QueryExecutorTaskProvider implements IQueryExecutorTaskProvider{

	private final ITaskExecutorEngine taskEngine;
	private final QueryManager queryManager;
	
	@Autowired
	public QueryExecutorTaskProvider(ITaskExecutorEngine taskEngine, QueryManager queryManager) {
		this.taskEngine = taskEngine;
		this.queryManager = queryManager;
	}
	
	@Override
	public ITaskElement<JoinVoid> getTask(String query) {
		return new ATaskElement<JoinVoid>(taskEngine) {
			
			@Override
			public void preexecute() { /* do nothing */ }			

			@Override
			public void execute() {
				// TODO
			}	
		};
	}

}
