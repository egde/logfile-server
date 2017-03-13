package de.kazkazi.simple.logfileserver.restEndpoints;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import de.kazkazi.simple.logfileserver.repo.LogFileLocationRepo;
import de.kazkazi.simple.logfileserver.repo.LogFileTailerListenerManager;
import de.kazkazi.simple.logfileserver.restEndpoints.dto.StreamingRequest;

@Controller
public class LogFileWebSocketEndpoint {
	
	@Autowired
	LogFileLocationRepo logFileRepo;
	@Autowired
	LogFileTailerListenerManager logFileTailerListenerManager;

	@MessageMapping("/startStreaming")
	public void startStreaming(@Payload StreamingRequest streamingRequest, SimpMessageHeaderAccessor  headerAccessor) {
		String logFileHash = streamingRequest.getHash();
		String sessionId =  headerAccessor.getSessionAttributes().get("sessionId").toString();
		logFileTailerListenerManager.startTailing(logFileHash, sessionId);
	}
}
