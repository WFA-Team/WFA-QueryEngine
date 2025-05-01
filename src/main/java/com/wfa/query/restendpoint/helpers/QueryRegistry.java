package com.wfa.query.restendpoint.helpers;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

@Component
public class QueryRegistry {
	private final Map<String, String> queryIdToSessionUUID;
	
	public QueryRegistry() {
		queryIdToSessionUUID = new ConcurrentHashMap<String, String>();
	}
	
	public void register(String queryId, String sessionUUID) {
		queryIdToSessionUUID.put(queryId, sessionUUID);
	}
	
	public void removeQuery(String queryId) {
		queryIdToSessionUUID.remove(queryId);
	}
	
	public String getSessionUUID(String queryId) {
		return queryIdToSessionUUID.get(queryId);
	}
	
	public void removeAllQueriesForSession(String sessionUUID) {
		queryIdToSessionUUID.entrySet().removeIf(entry -> entry.getValue().equals(sessionUUID));
	}
}
