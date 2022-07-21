package com.lawencon.community.dto.threadheaderpolling;

import javax.validation.constraints.NotNull;

public class InsertThreadHeaderPollingReq {
	
	@NotNull
	private String type;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
