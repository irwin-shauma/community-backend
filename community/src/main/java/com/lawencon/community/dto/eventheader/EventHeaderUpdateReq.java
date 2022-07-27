package com.lawencon.community.dto.eventheader;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class EventHeaderUpdateReq {

	@NotNull(message = "ID can't be empty")
	private String id;

	@NotBlank(message = "Event Type Id can't be empty")
	private String eventTypeId;
	
	@NotBlank(message = "Title can't be empty")
	private String title;

	@NotBlank(message = "Active can't be empty")
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
}
