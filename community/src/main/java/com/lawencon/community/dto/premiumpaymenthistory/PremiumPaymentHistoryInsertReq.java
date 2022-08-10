package com.lawencon.community.dto.premiumpaymenthistory;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PremiumPaymentHistoryInsertReq {

	@NotNull(message = "File Can't be Empty")
	private String fileName;
	private String fileExtension;

	@NotBlank(message = "PremiumType Id can't be empty")
	private String premiumTypeId;

	public String getPremiumTypeId() {
		return premiumTypeId;
	}

	public void setPremiumTypeId(String premiumTypeId) {
		this.premiumTypeId = premiumTypeId;
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

}
