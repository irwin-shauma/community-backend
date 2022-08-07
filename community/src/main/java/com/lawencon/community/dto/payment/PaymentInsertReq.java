package com.lawencon.community.dto.payment;

import javax.validation.constraints.NotNull;

public class PaymentInsertReq {
	
	@NotNull(message = "File Can't be Empty")
	private String fileName;
	private String fileExtension;

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
