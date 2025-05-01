package com.wfa.query.restendpoint.helpers;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SessionRegistry {

    private final Map<String, String> uuidToSession;
    private final Map<String, String> sessionToUuid;
    private final QueryRegistry queryRegistry;

    @Autowired
    public SessionRegistry(QueryRegistry queryRegistry) {
    	uuidToSession = new ConcurrentHashMap<String, String>();
    	sessionToUuid = new ConcurrentHashMap<String, String>();
    	this.queryRegistry = queryRegistry;
    }
    
    public void register(String sessionId, String uuid) {
        uuidToSession.put(uuid, sessionId);
        sessionToUuid.put(sessionId, uuid);
    }

    public String getSessionId(String uuid) {
        return uuidToSession.get(uuid);
    }

    public void removeBySessionId(String sessionId) {
        String uuid = sessionToUuid.remove(sessionId);
        if (uuid != null) {
        	this.queryRegistry.removeAllQueriesForSession(uuid);
            uuidToSession.remove(uuid);
        }
    }

    public String getUUID(String sessionId) {
        return sessionToUuid.get(sessionId);
    }
}
