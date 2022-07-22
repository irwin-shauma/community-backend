package com.lawencon.community.dto.threadpollingdetail;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ThreadPollingDetailUpdateReq {

	@NotNull(message = "ID can't be empty")
	private String id;

	@NotNull(message = "Thread header polling id can't be empty")
	private String threadHeaderPollingId;

	@NotBlank(message = "Question can't be empty")
	private String question;

	@NotNull(message = "Active status can't be empty")
	private Boolean isActive;

	@NotNull(message = "Version can't be empty")
	private Integer version;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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
