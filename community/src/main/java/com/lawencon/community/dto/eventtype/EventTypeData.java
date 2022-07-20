package com.lawencon.community.dto.eventtype;

public class EventTypeData {

	private Long id;
	private String threadHeaderCode;
	private Long threadHeaderId;
	private Long fileId;
	private Long userId;
	private String commentThread;
	private Boolean isActive;
	private Integer version;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getThreadHeaderCode() {
		return threadHeaderCode;
	}

	public void setThreadHeaderCode(String threadHeaderCode) {
		this.threadHeaderCode = threadHeaderCode;
	}

	public Long getThreadHeaderId() {
		return threadHeaderId;
	}

	public void setThreadHeaderId(Long threadHeaderId) {
		this.threadHeaderId = threadHeaderId;
	}

	public Long getFileId() {
		return fileId;
	}

	public void setFileId(Long fileId) {
		this.fileId = fileId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getCommentThread() {
		return commentThread;
	}

	public void setCommentThread(String commentThread) {
		this.commentThread = commentThread;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	
}
