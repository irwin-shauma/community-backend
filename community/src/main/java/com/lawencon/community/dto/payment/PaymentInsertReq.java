package com.lawencon.community.dto.payment;

import javax.validation.constraints.NotNull;

public class PaymentInsertReq {

	@NotNull(message = "File Can't be Empty")
	private String fileName;
	private String fileExtension;

	private String premiumTypeId;

	private String eventHeaderId;

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

	public String getPremiumTypeId() {
		return premiumTypeId;
	}

	public void setPremiumTypeId(String premiumTypeId) {
		this.premiumTypeId = premiumTypeId;
	}

	public String getEventHeaderId() {
		return eventHeaderId;
	}

	public void setEventHeaderId(String eventHeaderId) {
		this.eventHeaderId = eventHeaderId;
	}

}
