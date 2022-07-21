package com.lawencon.community.dto.eventheader;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class EventHeaderInsertReq {
	
	@NotBlank(message = "Event Header Code can't be empty")
	@Size(min =3, max = 50, message = "Event Header Code size must be between 3 to 50")
	private String eventHeaderCode;
	
	@NotBlank(message = "Title can't be empty")
	private String title;
	
	@NotBlank(message = "Tvent type can't be empty")
	private String eventTypeId;

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

	
}
