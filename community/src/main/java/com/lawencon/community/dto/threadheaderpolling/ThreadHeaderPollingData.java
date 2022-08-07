package com.lawencon.community.dto.threadheaderpolling;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class ThreadHeaderPollingData {

	private String id;
	private String titlePolling;
	private String contentPolling;
	private String pollingQuestion;
	private LocalDate duration;
	private Boolean isActive;
	private String fullName;
	private String userId;
	private String fileId;
	private String userPhoto;
	private LocalDateTime createdAt;
	private String createdBy;
	private Boolean isChoice;
	private Integer version;
	private List<ThreadPollingDetailData> threadDtlPolling;
	private Integer countAllAnswer;
	

	
	
	
	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
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

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

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

	public String getPollingQuestion() {
		return pollingQuestion;
	}

	public void setPollingQuestion(String pollingQuestion) {
		this.pollingQuestion = pollingQuestion;
	}

	public LocalDate getDuration() {
		return duration;
	}

	public void setDuration(LocalDate duration) {
		this.duration = duration;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Boolean getIsChoice() {
		return isChoice;
	}

	public void setIsChoice(Boolean isChoice) {
		this.isChoice = isChoice;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public List<ThreadPollingDetailData> getThreadDtlPolling() {
		return threadDtlPolling;
	}

	public void setThreadDtlPolling(List<ThreadPollingDetailData> threadDtlPolling) {
		this.threadDtlPolling = threadDtlPolling;
	}

	public Integer getCountAllAnswer() {
		return countAllAnswer;
	}

	public void setCountAllAnswer(Integer countAllAnswer) {
		this.countAllAnswer = countAllAnswer;
	}

}
