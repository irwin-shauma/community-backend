package com.lawencon.community.dto.eventheader;

import java.time.LocalDateTime;
import java.util.Date;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;

public class EventHeaderInsertReq {

	@NotBlank(message = "Title can't be empty")
	private String title;

	@NotBlank(message = "Event type can't be empty")
	private String eventTypeId;

	private String fileName;
	private String fileExtension;

	private Float price;

	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'+07:00'")
	private Date starts;

	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'+07:00'")
	private Date ends;

	private String provider;
	private String location;

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

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public Date getStarts() {
		return starts;
	}

	public void setStarts(Date starts) {
		this.starts = starts;
	}

	public Date getEnds() {
		return ends;
	}

	public void setEnds(Date ends) {
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
