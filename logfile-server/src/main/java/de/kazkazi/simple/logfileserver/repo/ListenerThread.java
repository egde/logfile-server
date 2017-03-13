package de.kazkazi.simple.logfileserver.repo;

import java.util.HashSet;

public class ListenerThread extends Thread {

	public final HashSet<String> stompSessions = new HashSet<>();
	private String logFileHash;
	
	public ListenerThread(String stompSessionId, Runnable runnable, String logFileHash) {
		super(runnable);
		stompSessions.add(stompSessionId);
		this.logFileHash = logFileHash;
	}
	
	public String removeSession(String stompSessionId) {
		if (stompSessions.remove(stompSessionId)) {
			return stompSessionId;
		} else {
			return null;
		}
	}
	
	public boolean hasNoSession() {
		return stompSessions.isEmpty();
	}

	public void addSession(String stompSessionId) {
		stompSessions.add(stompSessionId);
	}

	public boolean hasSession(String stompSessionId) {
		return stompSessions.contains(stompSessionId);
	}

	public String getLogFileHash() {
		return logFileHash;
	}

	public void setLogFileHash(String logFileHash) {
		this.logFileHash = logFileHash;
	}
}
