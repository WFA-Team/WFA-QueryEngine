package com.wfa.query.tasks.api;

public interface IQueryEngineTaskProviderRepository {
	<T> T getTaskProvider(Class<T> type);
}
