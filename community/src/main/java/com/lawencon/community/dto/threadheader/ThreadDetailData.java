package com.lawencon.community.dto.threadheader;

import java.time.LocalDateTime;

public class ThreadDetailData {

	private String id;
	private String threadHeaderId;
	private String userId;
	private String fullName;
	private String userPhoto;
	private String commentThread;
	private LocalDateTime createdAt;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getThreadHeaderId() {
		return threadHeaderId;
	}

	public void setThreadHeaderId(String threadHeaderId) {
		this.threadHeaderId = threadHeaderId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPhoto() {
		return userPhoto;
	}

	public void setUserPhoto(String userPhoto) {
		this.userPhoto = userPhoto;
	}

	public String getCommentThread() {
		return commentThread;
	}

	public void setCommentThread(String commentThread) {
		this.commentThread = commentThread;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

}
