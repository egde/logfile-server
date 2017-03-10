package de.kazkazi.simple.logfileserver.restEndpoints;

import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import de.kazkazi.simple.logfileserver.repo.LogFileLocationRepo;
import de.kazkazi.simple.logfileserver.restEndpoints.dto.LogFileLocation;

@RestController
public class LogFileLocationEndpoint {
	
	@Autowired
	LogFileLocationRepo logFileLocationRepo;
	
	@RequestMapping(method=RequestMethod.GET, value="/api/logFileLocations")
	public ArrayList<LogFileLocation> getFileLocations() {
		return logFileLocationRepo.listAll();
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/api/logFileLocations")
	public LogFileLocation insertLogFileLocation(@RequestBody(required=true) LogFileLocation logFileLocation) {
		return logFileLocationRepo.add(logFileLocation.getPath());
	}

}
