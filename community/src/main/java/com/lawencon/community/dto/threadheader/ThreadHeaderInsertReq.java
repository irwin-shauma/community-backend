package com.lawencon.community.dto.threadheader;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ThreadHeaderInsertReq {

	@NotBlank(message = "Title can't be empty")
	private String title;
	
	@NotNull(message = "Thread type can't be empty")
	private String threadTypeId;
	
	@NotBlank(message = "Content can't be empty")
	private String contentThread;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getThreadTypeId() {
		return threadTypeId;
	}
	public void setThreadTypeId(String threadTypeId) {
		this.threadTypeId = threadTypeId;
	}
	public String getContentThread() {
		return contentThread;
	}
	public void setContentThread(String contentThread) {
		this.contentThread = contentThread;
	}



}
