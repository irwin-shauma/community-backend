package com.lawencon.community.dto.threaddetail;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UpdateThreadDetailReq {

	@NotNull(message = "ID can't be empty")
	private Long id;
	
	@NotNull(message = "ID can't be empty")
	private Long threadHeaderId;
	
	@NotNull(message = "ID can't be empty")
	private Long fileId;
	
	@NotNull(message = "ID can't be empty")
	private Long userId;

	@NotBlank(message = "Comments can't be empty")
	@Size(min = 1, max = 10000, message = "Comment must be between 1 to 10000 characters ")
	private String commentThread;
	
	@NotNull(message = "Active status can't be empty")
	private Boolean isActive;

	@NotNull(message = "Version can't be empty")
	private Integer version;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
