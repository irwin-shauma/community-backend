package com.lawencon.community.dto.threadpollinganswer;

import javax.validation.constraints.NotNull;

public class ThreadPollingAnswerInsertReq {

	@NotNull(message = "Thread Polling Id can't be empty")
	private String threadPollingId;

	
	public String getThreadPollingId() {
		return threadPollingId;
	}

	public void setThreadPollingId(String threadPollingId) {
		this.threadPollingId = threadPollingId;
	}

	
}
