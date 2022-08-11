package com.lawencon.community.dto.eventpaymenthistory;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class EventPaymentHistoryInsertReq {

	@NotNull(message = "File Can't be Empty")
	private String fileName;
	private String fileExtension;

	@NotBlank(message = "Event Id can't be empty")
	private String eventHeaderId;

	public String getEventHeaderId() {
		return eventHeaderId;
	}

	public void setEventHeaderId(String eventHeaderId) {
		this.eventHeaderId = eventHeaderId;
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
