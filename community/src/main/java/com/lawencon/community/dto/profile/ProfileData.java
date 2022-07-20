package com.lawencon.community.dto.profile;

import java.time.LocalDateTime;

public class ProfileData {

	private String id;
	private String profileCode;
	private String userId;
	private String fullName;
	private String company;
	private String industry;
	private String position;
	private String status;
	private String fileId;
	private LocalDateTime statusDuration;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProfileCode() {
		return profileCode;
	}

	public void setProfileCode(String profileCode) {
		this.profileCode = profileCode;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public LocalDateTime getStatusDuration() {
		return statusDuration;
	}

	public void setStatusDuration(LocalDateTime statusDuration) {
		this.statusDuration = statusDuration;
	}

}
