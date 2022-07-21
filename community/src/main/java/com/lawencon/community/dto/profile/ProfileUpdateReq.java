package com.lawencon.community.dto.profile;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

public class UpdateProfileReq {

	@NotNull(message = "Id Can't be Empty")
	private String id;
	private String userId;

	@NotBlank(message = "Fullname can't be Blank")
	private String fullName;

	@NotBlank(message = "Company can't be Blank")
	private String company;

	@NotBlank(message = "Industry can't be Blank")
	private String industry;

	@NotBlank(message = "Position can't be Blank")
	private String position;
	private String fileId;

	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.000'Z'")
	private LocalDateTime statusDuration;

	private Integer version;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

}
