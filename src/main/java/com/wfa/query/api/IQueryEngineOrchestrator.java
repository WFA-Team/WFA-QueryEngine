package com.wfa.query.api;

import java.util.List;

public interface IQueryEngineOrchestrator {
	void createStream(String streamName, String topic, List<String> fieldNames);
	void executeQuery(String query);
	void executeQueryRT(String query);
	void exit();
}
