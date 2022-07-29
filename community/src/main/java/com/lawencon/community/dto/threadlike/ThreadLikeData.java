package com.lawencon.community.dto.threadlike;

public class ThreadLikeData {

	private String id;
	private String threadLikeCode;
	private String userId;
	private String threadId;
	private Boolean isActive;
	private Integer version;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getThreadLikeCode() {
		return threadLikeCode;
	}

	public void setThreadLikeCode(String threadLikeCode) {
		this.threadLikeCode = threadLikeCode;
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
