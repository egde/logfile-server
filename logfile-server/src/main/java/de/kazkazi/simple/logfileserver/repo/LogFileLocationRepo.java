package de.kazkazi.simple.logfileserver.repo;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import de.kazkazi.simple.logfileserver.AppConfig;
import de.kazkazi.simple.logfileserver.restEndpoints.dto.LogFileLocation;

@Component
public class LogFileLocationRepo {
	
	private ConcurrentHashMap<String, LogFileLocation> logFileLocations;
	@Autowired
	private AppConfig appConfig;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(LogFileLocationRepo.class);
	
	
	public LogFileLocationRepo(@Autowired AppConfig appConfig) {
		logFileLocations = new ConcurrentHashMap<String, LogFileLocation>();
		this.appConfig = appConfig;

		List<LogFileLocation> locations = load();
		for (LogFileLocation loc : locations) {
			logFileLocations.put(loc.getHash(), loc);
		}
	}
	
	public LogFileLocation add(String path) {
		String uuid = UUID.randomUUID().toString();
		LogFileLocation l = new LogFileLocation(path, uuid);
		logFileLocations.put(l.getHash(), l);
		persist();
		return l;
	}

	private synchronized void persist() {
		if (StringUtils.isNotEmpty(appConfig.getLogFileConfigPath())) {
			ObjectMapper mapper = new ObjectMapper();
			try {
				mapper.writeValue(new File(appConfig.getLogFileConfigPath()), logFileLocations.values());
			} catch (IOException e) {
				LOGGER.error("Unable to write the Log Configuration File", e);
			}
		}
	}
	
	private synchronized List<LogFileLocation> load() {
		List<LogFileLocation> result = new ArrayList<>(0);
		if (StringUtils.isNotEmpty(appConfig.getLogFileConfigPath())) {
			ObjectMapper mapper = new ObjectMapper();
			try {
				result = mapper.readValue(new File(appConfig.getLogFileConfigPath()), new TypeReference<List<LogFileLocation>>(){});
				LOGGER.info("Read in Log File Configurations from "+appConfig.getLogFileConfigPath());
			} catch (IOException e) {
				LOGGER.error("Unable to read the Log Configuration File", e);
			}
		}
		
		return result;
	}

	public LogFileLocation remove(String hash) {
		LogFileLocation result = logFileLocations.remove(hash);
		persist();
		return result;
	}
	
	public ArrayList<LogFileLocation> listAll() {
		ArrayList<LogFileLocation> result = new ArrayList<LogFileLocation>(logFileLocations.values());
		return result;
	}
	
	public LogFileLocation getLogFile(String hash) {
		return logFileLocations.get(hash);
	}

}
