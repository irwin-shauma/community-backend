package com.lawencon.community.dto.eventtype;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class EventTypeUpdateReq {

	@NotNull
	private String id;

	@NotBlank
	private String type;
	private Boolean isActive;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

}
