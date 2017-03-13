package de.kazkazi.simple.logfileserver.repo;

import java.io.File;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.io.input.Tailer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import de.kazkazi.simple.logfileserver.restEndpoints.LogFileTailerListener;
import de.kazkazi.simple.logfileserver.restEndpoints.dto.LogFileLocation;

@Component
public class LogFileTailerListenerManager {

	private ConcurrentHashMap<String, ListenerThread> listenerThreads = new ConcurrentHashMap<>();
	@Autowired
	private LogFileLocationRepo logFileLocationRepo;
	@Autowired
	private ApplicationContext appCtx;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(LogFileTailerListenerManager.class);
	
	public void startTailing(String logFileHash, String stompSessionId) {
		synchronized(listenerThreads) {
			 ListenerThread tailerListenerThread = listenerThreads.get(logFileHash);
		    if (tailerListenerThread == null) {
		    	LOGGER.info("Starting a new listener for log file "+logFileHash);
		    	LogFileLocation logFile = logFileLocationRepo.getLogFile(logFileHash);
		    	if (logFile == null) {
		    		LOGGER.warn("Log file was not found");
		    		return;
		    	}
				String path = logFile.getPath();
		    	File file = new File(path);
		    	LogFileTailerListener tailerListener = appCtx.getBean(LogFileTailerListener.class, logFileHash);
		    	Tailer tailer = new Tailer(file, tailerListener, 1000);
		    	ListenerThread thread = new ListenerThread(stompSessionId, tailer, logFileHash);
		    	thread.setDaemon(true); // optional
		    	thread.start();
		    	listenerThreads.put(logFileHash, thread);
		    } else {
		    	LOGGER.info("Listener started for log file "+logFileHash);
		    	tailerListenerThread.addSession(stompSessionId);
		    }
		}
	}
	
	public void stopTailing(String stompSessionId) {
		String logFileHash;
		synchronized(listenerThreads) {
			for (ListenerThread tailerListenerThread: listenerThreads.values()) {
				if (tailerListenerThread.hasSession(stompSessionId)) {
					logFileHash = tailerListenerThread.getLogFileHash();
					LOGGER.info(String.format("Removing session %s listener for log file %s",stompSessionId, logFileHash));
					tailerListenerThread.removeSession(stompSessionId);
					if (tailerListenerThread.hasNoSession()) {
						LOGGER.info("Stopping listener for log file "+logFileHash);
						tailerListenerThread.interrupt();
						listenerThreads.remove(logFileHash);
					}
				}
			}
		}
	}
	
}
