package com.wfa.query.tasks.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import com.wfa.middleware.taskexecutor.api.TaskProvider;
import com.wfa.query.tasks.api.IQueryEngineTaskProviderRepository;
import com.wfa.query.tasks.api.IQueryExecutorTaskProvider;
import com.wfa.query.tasks.api.IShutdownSequenceTaskProvider;
import com.wfa.query.tasks.api.IStreamCreatorTaskProvider;

@Component
public class QueryEngineTaskProviderRespository implements IQueryEngineTaskProviderRepository{
	@SuppressWarnings("rawtypes")
	private final Map<Class, Object> taskRepo;
	
	@SuppressWarnings("rawtypes")
	@Autowired
	QueryEngineTaskProviderRespository(ConfigurableApplicationContext ctx) {
		taskRepo = new HashMap<Class, Object>();
		Map<String, Object> taskProviders = ctx.getBeansWithAnnotation(TaskProvider.class);
		for (Entry<String, Object> entry : taskProviders.entrySet()) {
			if (entry.getValue() instanceof IShutdownSequenceTaskProvider)
				taskRepo.put(IShutdownSequenceTaskProvider.class, entry.getValue());
			else if (entry.getValue() instanceof IQueryExecutorTaskProvider)
				taskRepo.put(IQueryExecutorTaskProvider.class, entry.getValue());
			else if (entry.getValue() instanceof IStreamCreatorTaskProvider)
				taskRepo.put(IStreamCreatorTaskProvider.class, entry.getValue());
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <T> T getTaskProvider(Class<T> type) {
		if (type.isInstance(taskRepo.get(type)))
			return (T)(taskRepo.get(type));
		return null;
	}		
}
