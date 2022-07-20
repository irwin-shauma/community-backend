package com.lawencon.community.dto.eventtype;

import javax.validation.constraints.NotNull;

public class InsertThreadTypeReq {
	
	@NotNull
	private String type;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
	
	 
	
}
