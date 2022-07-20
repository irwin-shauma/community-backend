package com.lawencon.community.dto.bookmark;

import javax.validation.constraints.NotNull;

public class UpdateBookmarkReq {

	@NotNull(message = "Id Can't be Null")
	private String id;

	@NotNull(message = "User Can't Be Null")
	private String userId;

	@NotNull(message = "Thread Can't Be Null")
	private String threadId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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
