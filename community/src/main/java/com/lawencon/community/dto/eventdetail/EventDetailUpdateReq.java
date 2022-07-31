package com.lawencon.community.dto.eventdetail;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

public class EventDetailUpdateReq {

	@NotNull(message = "ID can't be empty")
	private String id;

	@NotBlank(message = "Event Header Id can't be empty")
	private String eventHeaderId;

	private String fileId;

	@NotNull(message = "Price can't be empty")
	private Float price;

	@NotNull(message = "Start date can't be empty")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime startDate;

	@NotNull(message = "End date can't be empty")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime endDate;

	@NotBlank(message = "Provider can't be null")
	private String provider;

	@NotBlank(message = "Location can't be null")
	private String locations;

	@NotBlank(message = "Active can't be empty")
	private Boolean isActive;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEventHeaderId() {
		return eventHeaderId;
	}

	public void setEventHeaderId(String eventHeaderId) {
		this.eventHeaderId = eventHeaderId;
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public LocalDateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}

	public LocalDateTime getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
	}

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	public String getLocations() {
		return locations;
	}

	public void setLocations(String locations) {
		this.locations = locations;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
}
