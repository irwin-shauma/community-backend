package com.lawencon.community.dto.threadheader;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ThreadHeaderUpdateReq {

	private String id;
	
	@NotBlank(message = "Title can't be empty")
	private String title;
	
	@NotBlank(message = "Content cant' be empty")
	private String contentThread;
	
	@NotNull(message = "Thread type can't be empty")
	private String threadTypeId;
	private Integer version;
	private Boolean isActive;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContentThread() {
		return contentThread;
	}

	public void setContentThread(String contentThread) {
		this.contentThread = contentThread;
	}

	public String getThreadTypeId() {
		return threadTypeId;
	}

	public void setThreadTypeId(String threadTypeId) {
		this.threadTypeId = threadTypeId;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

}
