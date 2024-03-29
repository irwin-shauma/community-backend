package com.lawencon.community.dto.threaddetail;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ThreadDetailInsertReq {
	
	@NotNull(message = "Thread header can't be empty")
	private String threadHeaderId;
	
	@NotBlank(message = "Comments can't be empty")
	@Size(min = 1, max = 10000, message = "Comment must be between 1 to 10000 characters")
	private String commentThread;

	public String getThreadHeaderId() {
		return threadHeaderId;
	}

	public void setThreadHeaderId(String threadHeaderId) {
		this.threadHeaderId = threadHeaderId;
	}

	public String getCommentThread() {
		return commentThread;
	}

	public void setCommentThread(String commentThread) {
		this.commentThread = commentThread;
	}


	
	 
	
}
