package com.wfa.query.restendpoint.websocket;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import com.wfa.query.restendpoint.helpers.SessionRegistry;

@Component
public class WebSocketEventListener {
	
    private final SimpMessagingTemplate messagingTemplate;
    private final SessionRegistry sessionRegistry;
    
    @Autowired
    public WebSocketEventListener(SimpMessagingTemplate messagingTemplate, SessionRegistry sessionRegistry) {
    	this.messagingTemplate = messagingTemplate;
    	this.sessionRegistry = sessionRegistry;
    }

    @EventListener
    public void handleSessionConnected(SessionConnectEvent event) {
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(event.getMessage());
        String sessionId = accessor.getSessionId();
        String uuid = UUID.randomUUID().toString();
        sessionRegistry.register(sessionId, uuid);

        // Send UUID to the client on their private queue
        messagingTemplate.convertAndSendToUser(sessionId, "/queue/uuid", uuid, createHeaders(sessionId));
    }

    @EventListener
    public void handleSessionDisconnect(SessionDisconnectEvent event) {
        String sessionId = event.getSessionId();
        sessionRegistry.removeBySessionId(sessionId);
    }

    private MessageHeaders createHeaders(String sessionId) {
        SimpMessageHeaderAccessor headerAccessor = SimpMessageHeaderAccessor.create();
        headerAccessor.setSessionId(sessionId);
        headerAccessor.setLeaveMutable(true);
        return headerAccessor.getMessageHeaders();
    }
}