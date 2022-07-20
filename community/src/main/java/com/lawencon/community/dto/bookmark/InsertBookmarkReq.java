package com.lawencon.community.dto.bookmark;

import javax.validation.constraints.NotNull;

public class InsertBookmarkReq {

	@NotNull(message = "User Can't Be Null")
	private String userId;
	
	@NotNull(message = "Thread Can't Be Null")
	private String threadId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getThreadId() {
		return threadId;
	}

	public void setThreadId(String threadId) {
		this.threadId = threadId;
	}

}
