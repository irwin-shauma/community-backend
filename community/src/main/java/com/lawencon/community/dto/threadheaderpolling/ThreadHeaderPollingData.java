package com.lawencon.community.dto.threadheaderpolling;

public class ThreadHeaderPollingData {

	private String id;
	private String titlePolling;
	private String contentPolling;
	private Boolean isActive;
	private Integer version;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitlePolling() {
		return titlePolling;
	}

	public void setTitlePolling(String titlePolling) {
		this.titlePolling = titlePolling;
	}

	public String getContentPolling() {
		return contentPolling;
	}

	public void setContentPolling(String contentPolling) {
		this.contentPolling = contentPolling;
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
