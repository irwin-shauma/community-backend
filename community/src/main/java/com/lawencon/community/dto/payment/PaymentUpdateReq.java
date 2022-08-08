package com.lawencon.community.dto.payment;

import javax.validation.constraints.NotNull;

public class PaymentUpdateReq {

	private String id;

	@NotNull(message = "Is Approve Can't be Empty")
	private Boolean isApprove;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Boolean getIsApprove() {
		return isApprove;
	}

	public void setIsApprove(Boolean isApprove) {
		this.isApprove = isApprove;
	}

}
