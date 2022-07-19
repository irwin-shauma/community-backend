package com.lawencon.community.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name="event_type", uniqueConstraints = {
		@UniqueConstraint(
				name="event_type_bk",
				columnNames="event_type_code"
				)
})
public class EventType extends BaseEntity {
	
	private static final long serialVersionUID = -5196455701225322056L;
	
	@Column(name="event_type_code")
	private String eventTypeCode;
	
	@Column(name="type")
	private String type;

	public String getEventTypeCode() {
		return eventTypeCode;
	}

	public void setEventTypeCode(String eventTypeCode) {
		this.eventTypeCode = eventTypeCode;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	

}
