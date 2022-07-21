package com.lawencon.community.dto.payment;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PaymentUpdateReq {
	
	@NotNull(message = "ID can't be empty")
	private String id;
	
	@NotBlank(message = "File Id can't be empty")
	private String fileId;
	
	@NotBlank(message = "User Id can't be empty")
	private String userId;
	
	@NotBlank(message = "Active can't be empty")
	private Boolean isActive;
	
	@NotBlank(message = " version can't be empty")
	private Integer version;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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
