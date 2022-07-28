package com.lawencon.community.dto.threadpollingdetail;

import javax.validation.constraints.NotBlank;

public class ThreadPollingDetailInsertReq {

	private String threadHeaderPollingId;

	@NotBlank(message = "Question can't be empty")
	private String question;

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

}
