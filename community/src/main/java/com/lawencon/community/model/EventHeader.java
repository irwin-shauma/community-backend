package com.lawencon.community.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "event_header", uniqueConstraints = {
		@UniqueConstraint(
				name = "event_header_bk",
				columnNames = "event_header_code"
				)
})
public class EventHeader extends BaseEntity {
	
	private static final long  serialVersionUID = -5196455701225322056L;
	
	@Column(name="event_header_code")
	private String eventHeaderCode;
	
	@Column(name = "title")
	private String title;
	
	@OneToOne
	@JoinColumn(name = "event_type_id")
	private EventType eventType;

	public String getEventHeaderCode() {
		return eventHeaderCode;
	}

	public void setEventHeaderCode(String eventHeaderCode) {
		this.eventHeaderCode = eventHeaderCode;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public EventType getEventType() {
		return eventType;
	}

	public void setEventTypeId(EventType eventType) {
		this.eventType = eventType;
	}

}