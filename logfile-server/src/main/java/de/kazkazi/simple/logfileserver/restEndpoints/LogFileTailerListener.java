package de.kazkazi.simple.logfileserver.restEndpoints;

import org.apache.commons.io.input.Tailer;
import org.apache.commons.io.input.TailerListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import de.kazkazi.simple.logfileserver.restEndpoints.dto.LogFileEntry;

@Component
@Scope("prototype")
public class LogFileTailerListener implements TailerListener {

	private static final Logger LOGGER = LoggerFactory.getLogger(LogFileTailerListener.class); 
	@Autowired
	private SimpMessagingTemplate messagingTemplate;
	private String logFileHash;
	
	public LogFileTailerListener(String logFileHash) {
		LOGGER.debug("LogFileTailerListener initialised for "+logFileHash);
		this.logFileHash = logFileHash;
	}

	@Override
	public void fileNotFound() {
		// TODO Auto-generated method stub

	}

	@Override
	public void fileRotated() {
		// TODO Auto-generated method stub

	}

	@Override
	public void handle(String line) {
		this.messagingTemplate.convertAndSend("/topic/streamLogFile/"+logFileHash, new LogFileEntry(line));
	}

	@Override
	public void handle(Exception arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void init(Tailer arg0) {
		// TODO Auto-generated method stub

	}

}
