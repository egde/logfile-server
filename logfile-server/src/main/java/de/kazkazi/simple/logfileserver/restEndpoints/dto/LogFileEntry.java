package de.kazkazi.simple.logfileserver.restEndpoints.dto;

public class LogFileEntry {

	private String logLine;

	public LogFileEntry(String logLine) {
		super();
		this.logLine = logLine;
	}

	public LogFileEntry() {
		super();
	}

	public String getLogLine() {
		return logLine;
	}

	public void setLogLine(String logLine) {
		this.logLine = logLine;
	}
	
	
}
