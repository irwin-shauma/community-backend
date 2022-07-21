package com.lawencon.community.dto.eventtype;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class UpdateThreadTypeReq {

	@NotNull
	private String id;
	
	@NotBlank
	private String type;

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
	
	

	
	

}
