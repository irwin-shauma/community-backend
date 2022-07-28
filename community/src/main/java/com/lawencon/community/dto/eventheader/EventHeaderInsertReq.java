package com.lawencon.community.dto.eventheader;

import javax.validation.constraints.NotBlank;

public class EventHeaderInsertReq {

	@NotBlank(message = "Title can't be empty")
	private String title;

	@NotBlank(message = "Event type can't be empty")
	private String eventTypeId;

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
