package com.lawencon.community.dto.eventheader;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class EventHeaderUpdateReq {

	@NotNull(message = "ID can't be empty")
	private String id;

	@NotBlank(message = "Event Type Id can't be empty")
	private String eventTypeId;
	
	@NotNull(message = "User Id can't be empty")
	private String userId;
	
	private String fileId;
	
	@NotBlank(message = "Title can't be empty")
	private String title;
	
	private String fileName;
	private String fileExtension;

	@NotBlank(message = "Active status can't be empty")
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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
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
