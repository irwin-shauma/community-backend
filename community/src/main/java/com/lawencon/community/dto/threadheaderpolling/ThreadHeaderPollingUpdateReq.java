package com.lawencon.community.dto.threadheaderpolling;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ThreadHeaderPollingUpdateReq {
	@NotNull(message = "Id can't be empty")
	private String id;
	
	@NotBlank(message = "Title can't be empty")
	private String titlePolling;
	
	@NotBlank(message = "Content can't be empty")
	private String contentPolling;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitlePolling() {
		return titlePolling;
	}

	public void setTitlePolling(String titlePolling) {
		this.titlePolling = titlePolling;
	}

	public String getContentPolling() {
		return contentPolling;
	}

	public void setContentPolling(String contentPolling) {
		this.contentPolling = contentPolling;
	}
}
