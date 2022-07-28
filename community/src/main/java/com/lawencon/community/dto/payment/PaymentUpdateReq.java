package com.lawencon.community.dto.payment;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PaymentUpdateReq {

	@NotNull(message = "ID can't be empty")
	private String id;

	@NotBlank(message = "File Id can't be empty")
	private String fileId;

	private String fileName;
	private String fileExtension;

	@NotBlank(message = "User Id can't be empty")
	private String userId;

	@NotBlank(message = "Active can't be empty")
	private Boolean isActive;

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

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileExtension() {
		return fileExtension;
	}

	public void setFileExtension(String fileExtension) {
		this.fileExtension = fileExtension;
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
}
