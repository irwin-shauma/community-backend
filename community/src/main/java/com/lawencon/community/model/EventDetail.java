package com.lawencon.community.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "event_detail")
public class EventDetail extends BaseEntity {

	private static final long serialVersionUID = -5196455701225322056L;

	@Column(name = "event_detail_code")
	private String eventDetailCode;

	@OneToOne
	@JoinColumn(name = "event_header_id")
	private EventHeader eventHeader;

	@Column(name = "price")
	private Float price;

	@Column(name = "start_date")
	private LocalDateTime startDate;

	@Column(name = "end_date")
	private LocalDateTime endDate;

	@Column(name = "provider")
	private String provider;

	@Column(name = "locations")
	private String locations;

	@OneToOne
	@JoinColumn(name = "file_id")
	private File file;

	public String getEventDetailCode() {
		return eventDetailCode;
	}

	public void setEventDetailCode(String eventDetailCode) {
		this.eventDetailCode = eventDetailCode;
	}

	public EventHeader getEventHeader() {
		return eventHeader;
	}

	public void setEventHeader(EventHeader eventHeader) {
		this.eventHeader = eventHeader;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
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

}
