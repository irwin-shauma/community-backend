package com.lawencon.community.dto.threadpollinganswer;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ThreadPollingAnswerUpdateReq {
	
	@NotBlank(message = "Id can't be empty")
	private String id;

	@NotNull(message = "Thread Polling Id can't be empty")
	private String threadPollingId;

	@NotBlank(message = "User Id can't be empty")
	private String userId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getThreadPollingId() {
		return threadPollingId;
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


}
