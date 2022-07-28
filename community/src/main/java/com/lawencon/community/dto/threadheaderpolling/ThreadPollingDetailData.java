package com.lawencon.community.dto.threadheaderpolling;

public class ThreadPollingDetailData {

	private String id;
	private String threadPollingDetailCode;
	private String threadHeaderPollingId;
	private String question;
	private Boolean isActive;
	private Integer version;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getThreadPollingDetailCode() {
		return threadPollingDetailCode;
	}

	public void setThreadPollingDetailCode(String threadPollingDetailCode) {
		this.threadPollingDetailCode = threadPollingDetailCode;
	}

	public String getThreadHeaderPollingId() {
		return threadHeaderPollingId;
	}

	public void setThreadHeaderPollingId(String threadHeaderPollingId) {
		this.threadHeaderPollingId = threadHeaderPollingId;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
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
