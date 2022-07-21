package com.lawencon.community.dto.eventtype;

import javax.validation.constraints.NotBlank;

public class EventTypeInsertReq {
	
	@NotBlank(message = "Type can't be empty")
	private String type;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
	
	 
	
}
