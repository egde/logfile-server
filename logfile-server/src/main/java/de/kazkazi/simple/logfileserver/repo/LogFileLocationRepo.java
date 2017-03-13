package de.kazkazi.simple.logfileserver.repo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import org.springframework.stereotype.Component;

import de.kazkazi.simple.logfileserver.restEndpoints.dto.LogFileLocation;

@Component
public class LogFileLocationRepo {
	
	private HashMap<String, LogFileLocation> logFileLocations;
	
	public LogFileLocationRepo() {
		logFileLocations = new HashMap<String, LogFileLocation>();
	}
	
	public LogFileLocation add(String path) {
		String uuid = UUID.randomUUID().toString();
		LogFileLocation l = new LogFileLocation(path, uuid);
		logFileLocations.put(l.getHash(), l);
		return l;
	}

	public LogFileLocation remove(String hash) {
		return logFileLocations.remove(hash);
	}
	
	public ArrayList<LogFileLocation> listAll() {
		ArrayList<LogFileLocation> result = new ArrayList<LogFileLocation>(logFileLocations.values());
		return result;
	}
	
	public LogFileLocation getLogFile(String hash) {
		return logFileLocations.get(hash);
	}

}
