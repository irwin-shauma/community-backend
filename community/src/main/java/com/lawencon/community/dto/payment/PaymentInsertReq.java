package com.lawencon.community.dto.payment;

import javax.validation.constraints.NotBlank;

public class PaymentInsertReq {
	
	
	@NotBlank(message = "User Id can't be empty")
	private String userId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
	
	
}
