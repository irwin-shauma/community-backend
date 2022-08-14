package com.lawencon.community.dto.threadpollinglike;

import javax.validation.constraints.NotNull;

public class ThreadPollingLikeUpdateReq {

	@NotNull(message = "ID can't be empty")
	private String id;

	@NotNull(message = "User can't be empty")
	private String userId;

	@NotNull(message = "Thread can't be empty")
	private String threadPollingHeaderId;

	private Boolean isActive;
	private Integer version;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

}
