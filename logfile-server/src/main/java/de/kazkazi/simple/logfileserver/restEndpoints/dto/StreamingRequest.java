package de.kazkazi.simple.logfileserver.restEndpoints.dto;

public class StreamingRequest {

	private String hash;
	private String stompSessionId;

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public String getStompSessionId() {
		return stompSessionId;
	}

	public void setStompSessionId(String stompSessionId) {
		this.stompSessionId = stompSessionId;
	}
}
