package com.lawencon.community.dto.threadpollinganswer;

public class ThreadPollingAnswerData {

	private String id;
	private String threadPollingAnswerCode;
	private String threadPollingId;
	private String userId;
	private Boolean isActive;
	private Integer version;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getThreadPollingId() {
		return threadPollingId;
	}
	
	public String getThreadPollingAnswerCode() {
		return threadPollingAnswerCode;
	}

	public void setThreadPollingAnswerCode(String threadPollingAnswerCode) {
		this.threadPollingAnswerCode = threadPollingAnswerCode;
	}

	public void setThreadPollingId(String threadPollingId) {
		this.threadPollingId = threadPollingId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	

	
}
