package com.lawencon.community.dto.threaddetail;

public class ThreadDetailData {

	private String id;
	private String threadHeaderCode;
	private String threadHeaderId;
	private String fileId;
	private String userId;
	private String commentThread;
	private Boolean isActive;
	private Integer version;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getThreadHeaderCode() {
		return threadHeaderCode;
	}

	public void setThreadHeaderCode(String threadHeaderCode) {
		this.threadHeaderCode = threadHeaderCode;
	}

	public String getThreadHeaderId() {
		return threadHeaderId;
	}

	public void setThreadHeaderId(String threadHeaderId) {
		this.threadHeaderId = threadHeaderId;
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
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
