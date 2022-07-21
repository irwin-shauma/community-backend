package com.lawencon.community.dto.threadpollingdetail;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ThreadPollingDetailInsertReq {

	@NotNull(message = "Thread Polling Id can't be empty")
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