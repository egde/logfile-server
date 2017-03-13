package de.kazkazi.simple.logfileserver.restEndpoints;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.stomp.StompBrokerRelayMessageHandler;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import de.kazkazi.simple.logfileserver.repo.LogFileTailerListenerManager;

@Component
public class WebSocketSessionDisconnect implements ApplicationListener<SessionDisconnectEvent> {

	@Autowired
	LogFileTailerListenerManager logFileTailerListenerManager;
	
	public void onApplicationEvent(SessionDisconnectEvent event) {
		StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
		
		String sessionId =headerAccessor.getSessionAttributes().get("sessionId").toString();
		logFileTailerListenerManager.stopTailing(sessionId);
	}
}
