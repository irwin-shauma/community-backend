package com.lawencon.community.dto.threadtype;

import javax.validation.constraints.NotBlank;

public class ThreadTypeInsertReq {
	
	@NotBlank(message="Thread type can't be empty")
	private String threadType;

	public String getThreadType() {
		return threadType;
	}

	public void setThreadType(String threadType) {
		this.threadType = threadType;
	}
	

}
