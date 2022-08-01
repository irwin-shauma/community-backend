package com.lawencon.community.dto.threadheader;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ThreadHeaderInsertReq {

	@NotBlank(message = "Title can't be empty")
	private String title;

	@NotNull(message = "Thread type can't be empty")
	private String threadTypeId;

	@NotNull(message = "User Id can't be empty")
	private String userId;

	@NotBlank(message = "Content can't be empty")
	private String contentThread;

	private String fileName;
	private String fileExtension;

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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getContentThread() {
		return contentThread;
	}

	public void setContentThread(String contentThread) {
		this.contentThread = contentThread;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileExtension() {
		return fileExtension;
	}

	public void setFileExtension(String fileExtension) {
		this.fileExtension = fileExtension;
	}

}
