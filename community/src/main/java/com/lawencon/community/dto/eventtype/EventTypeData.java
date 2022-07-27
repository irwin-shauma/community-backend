package com.lawencon.community.dto.eventtype;

public class EventTypeData {

	private String id;
	private String threadTypeCode;
	private String type;
	private Boolean isActive;
	private Integer version;

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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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
