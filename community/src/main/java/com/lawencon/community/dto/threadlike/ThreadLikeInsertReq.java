package com.lawencon.community.dto.threadlike;

import javax.validation.constraints.NotNull;

public class ThreadLikeInsertReq {



	@NotNull(message = "Thread can't be empty")
	private String threadId;



	public String getThreadId() {
		return threadId;
	}

	public void setThreadId(String threadId) {
		this.threadId = threadId;
	}

}
