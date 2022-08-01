package com.lawencon.community.dto.eventheader;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class EventHeaderInsertReq {

	@NotBlank(message = "Title can't be empty")
	private String title;

	@NotBlank(message = "Event type can't be empty")
	private String eventTypeId;
	
	@NotNull(message = "User Id can't be empty")
	private String userId;

	private String fileName;
	private String fileExtension;

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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileExtension() {
		return fileExtension;
	}

	public void setFileExtension(String fileExtension) {
		this.fileExtension = fileExtension;
	}

}
