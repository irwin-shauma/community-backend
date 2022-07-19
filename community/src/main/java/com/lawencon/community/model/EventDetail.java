package com.lawencon.community.model;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "event_detail", uniqueConstraints = {
		@UniqueConstraint(
				name = "event_detail_bk", 
				columnNames = "event_detail_bk"
			) 
		})
public class EventDetail extends BaseEntity {

	private static final long serialVersionUID = -5196455701225322056L;

	@Column(name = "event_detail_code")
	private String eventDetailCode;

	@OneToOne
	@JoinColumn(name = "event_header_id")
	private String eventHeaderId;

	@OneToOne
	@JoinColumn(name = "file_id")
	private File file;

	private Float price;
	private LocalDate dates;
	private LocalTime starts;
	private LocalTime ends;
	private String provider;
	private String locations;

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

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
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

}
