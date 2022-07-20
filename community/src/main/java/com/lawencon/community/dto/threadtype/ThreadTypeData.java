package com.lawencon.community.dto.threadtype;

public class ThreadTypeData {

	private String id;
	private String threadTypeCode;
	private String threadType;
	private String version;
	private Boolean isActive;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getThreadTypeCode() {
		return threadTypeCode;
	}

	public void setThreadTypeCode(String threadTypeCode) {
		this.threadTypeCode = threadTypeCode;
	}

	public String getThreadType() {
		return threadType;
	}

	public void setThreadType(String threadType) {
		this.threadType = threadType;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

}
