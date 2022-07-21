package com.lawencon.community.dto.eventheader;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class EventHeaderUpdateReq {

	@NotNull(message = "ID can't be empty")
	private String id;

	@NotBlank(message = "Event Header can't be empty")
	@Size(min = 3, max = 50, message = "Event Header Code must be between 3 to 50")
	private String eventHeaderCode;

	@NotBlank(message = "Event Type Id can't be empty")
	private String eventTypeId;
	
	@NotBlank(message = "Title can't be empty")
	private String title;

	@NotBlank(message = "Active can't be empty")
	private Boolean isActive;

	@NotBlank(message = " version can't be empty")
	private Integer version;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEventHeaderCode() {
		return eventHeaderCode;
	}

	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setEventHeaderCode(String eventHeaderCode) {
		this.eventHeaderCode = eventHeaderCode;
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
