package com.lawencon.community.dto.eventdetail;

import java.time.LocalDate;
import java.time.LocalTime;

public class EventDetailData {
	private Long id;
	private String eventDetailCode;
	private String eventHeaderId;
	private String fileId;
	private LocalDate dates;
	private LocalTime starts;
	private LocalTime ends;
	private String provider;
	private String locations;
	private Boolean isActive;
	private Integer version;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEventDetailCode() {
		return eventDetailCode;
	}

	public void setEventDetailCode(String eventDetailCode) {
		this.eventDetailCode = eventDetailCode;
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

	public LocalDate getDates() {
		return dates;
	}

	public void setDates(LocalDate dates) {
		this.dates = dates;
	}

	public LocalTime getStarts() {
		return starts;
	}

	public void setStarts(LocalTime starts) {
		this.starts = starts;
	}

	public LocalTime getEnds() {
		return ends;
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

	public void setEnds(LocalTime ends) {
		this.ends = ends;
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
