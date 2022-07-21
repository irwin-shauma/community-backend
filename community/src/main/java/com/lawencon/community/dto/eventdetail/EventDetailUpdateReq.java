package com.lawencon.community.dto.eventdetail;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class EventDetailUpdateReq {

	@NotNull(message = "ID can't be empty")
	private String id;

	@NotBlank(message = "Event Header Code can't be empty")
	@Size(min =3, max = 50, message = "Event Header Code size must be between 3 to 50")
	private String eventHeaderId;
	
	@NotNull
	private String fileId;
	
	@NotNull
	private Float price;
	
	@NotNull
	private LocalDate dates;
	
	@NotNull
	private LocalTime starts;
	
	@NotNull
	private LocalTime ends;
	
	@NotBlank(message = "Provider can't be null")
	private String provider;
	
	@NotBlank(message = "Location can't be null")
	private String locations;
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

	public void setEnds(LocalTime ends) {
		this.ends = ends;
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

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}
}
