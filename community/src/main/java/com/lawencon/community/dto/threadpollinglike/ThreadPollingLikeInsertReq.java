package com.lawencon.community.dto.threadpollinglike;

import javax.validation.constraints.NotNull;

public class ThreadPollingLikeInsertReq {

	@NotNull(message = "Thread can't be empty")
	private String threadPollingHeaderId;

	public String getThreadPollingHeaderId() {
		return threadPollingHeaderId;
	}

	public void setThreadPollingHeaderId(String threadPollingHeaderId) {
		this.threadPollingHeaderId = threadPollingHeaderId;
	}

}
