package com.lawencon.community.dto.threaddetail;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class InsertThreadDetailReq {
	
	@NotNull
	private Long fileId;
	
	@NotNull
	private Long userId;
	
	@NotBlank(message = "Comments can't be empty")
	@Size(min = 1, max = 10000, message = "Comment must be between 1 to 10000 characters")
	private String commentThread;

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
	
	 
	
}
