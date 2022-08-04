package com.lawencon.community.dto.eventheader;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

public class EventHeaderUpdateReq {

	@NotNull(message = "ID can't be empty")
	private String id;

	private String title;

	@NotBlank(message = "Event Type Id can't be empty")
	private String eventTypeId;

	@NotNull(message = "User Id can't be empty")
	private String userId;

	private String fileId;

	@NotBlank(message = "Title can't be empty")

	private String fileName;
	private String fileExtension;

	@NotBlank(message = "Active status can't be empty")
	private Boolean isActive;

	private Float price;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime starts;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime ends;

	private String provider;
	private String location;

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

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public LocalDateTime getStarts() {
		return starts;
	}

	public void setStarts(LocalDateTime starts) {
		this.starts = starts;
	}

	public LocalDateTime getEnds() {
		return ends;
	}

	public void setEnds(LocalDateTime ends) {
		this.ends = ends;
	}

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

}
