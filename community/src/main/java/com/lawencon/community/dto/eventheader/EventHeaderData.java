package com.lawencon.community.dto.eventheader;

public class EventHeaderData {
	private Long id;
	private String eventHeaderCode;
	private String title;
	private String eventTypeId;
	private Boolean isActive;
	private Integer version;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEventHeaderCode() {
		return eventHeaderCode;
	}

	public void setEventHeaderCode(String eventHeaderCode) {
		this.eventHeaderCode = eventHeaderCode;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getEventTypeId() {
		return eventTypeId;
	}

	public void setEventTypeId(String eventTypeId) {
		this.eventTypeId = eventTypeId;
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
