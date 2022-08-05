package com.lawencon.community.dto.threadtype;

import javax.validation.constraints.NotBlank;

public class ThreadTypeUpdateReq {

	private String id;
	@NotBlank(message = "Thread type can't be empty")
	private String threadType;
	private Boolean isActive;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getThreadType() {
		return threadType;
	}

	public void setThreadType(String threadType) {
		this.threadType = threadType;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

}
