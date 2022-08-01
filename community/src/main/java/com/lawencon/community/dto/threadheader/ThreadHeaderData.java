package com.lawencon.community.dto.threadheader;

import java.time.LocalDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ThreadHeaderData {

	private String id;
	private String threadTypeId;
	private String userId;
	private UUID threadHeaderCode;
	private String title;
	private String contentThread;
	private String fileId;
	private String fullName;
	private String createdBy;

	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private LocalDateTime createdAt;
	private Integer version;
	private Boolean isActive;
	private Integer countLike;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public UUID getThreadHeaderCode() {
		return threadHeaderCode;
	}

	public void setThreadHeaderCode(UUID threadHeaderCode) {
		this.threadHeaderCode = threadHeaderCode;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContentThread() {
		return contentThread;
	}

	public void setContentThread(String contentThread) {
		this.contentThread = contentThread;
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

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
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

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Integer getCountLike() {
		return countLike;
	}

	public void setCountLike(Integer countLike) {
		this.countLike = countLike;
	}

}
