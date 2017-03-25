package de.kazkazi.simple.logfileserver;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AppConfig {

	@Value("${app.logfileConfigPath:}")
	private String logFileConfigPath;

	public String getLogFileConfigPath() {
		return logFileConfigPath;
	}

	public void setLogFileConfigPath(String logFileConfigPath) {
		this.logFileConfigPath = logFileConfigPath;
	}
	
}
