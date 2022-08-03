package com.lawencon.community.dto.threadpollingdetail;

import javax.validation.constraints.NotBlank;

public class ThreadPollingDetailInsertReq {

	private String threadHeaderPollingId;

	@NotBlank(message = "Choice can't be empty")
	private String pollingChoice;

	public String getThreadHeaderPollingId() {
		return threadHeaderPollingId;
	}

	public void setThreadHeaderPollingId(String threadHeaderPollingId) {
		this.threadHeaderPollingId = threadHeaderPollingId;
	}

	public String getPollingChoice() {
		return pollingChoice;
	}

	public void setPollingChoice(String pollingChoice) {
		this.pollingChoice = pollingChoice;
	}

}
