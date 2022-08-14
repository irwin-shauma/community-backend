package com.lawencon.community.dto.threadpollinglike;

public class ThreadPollingLikeData {

	private String id;
	private String threadPollingLikeCode;
	private String userId;
	private String threadPollingHeaderId;
	private Boolean isActive;
	private Integer version;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getThreadPollingLikeCode() {
		return threadPollingLikeCode;
	}

	public void setThreadPollingLikeCode(String threadPollingLikeCode) {
		this.threadPollingLikeCode = threadPollingLikeCode;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getThreadPollingHeaderId() {
		return threadPollingHeaderId;
	}

	public void setThreadPollingHeaderId(String threadPollingHeaderId) {
		this.threadPollingHeaderId = threadPollingHeaderId;
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
