package com.lawencon.community.dto.threadheaderpolling;

import java.util.List;

public class ThreadHeaderPollingData {

	private String id;
	private String titlePolling;
	private String contentPolling;
	private String pollingQuestion;
	private Boolean isActive;
	private Integer version;
	private List<ThreadPollingDetailData> threadDtlPolling;
	private Integer countAllAnswer;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitlePolling() {
		return titlePolling;
	}

	public void setTitlePolling(String titlePolling) {
		this.titlePolling = titlePolling;
	}

	public String getContentPolling() {
		return contentPolling;
	}

	public void setContentPolling(String contentPolling) {
		this.contentPolling = contentPolling;
	}

	public String getPollingQuestion() {
		return pollingQuestion;
	}

	public void setPollingQuestion(String pollingQuestion) {
		this.pollingQuestion = pollingQuestion;
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

	public List<ThreadPollingDetailData> getThreadDtlPolling() {
		return threadDtlPolling;
	}

	public void setThreadDtlPolling(List<ThreadPollingDetailData> threadDtlPolling) {
		this.threadDtlPolling = threadDtlPolling;
	}

	public Integer getCountAllAnswer() {
		return countAllAnswer;
	}

	public void setCountAllAnswer(Integer countAllAnswer) {
		this.countAllAnswer = countAllAnswer;
	}

}
