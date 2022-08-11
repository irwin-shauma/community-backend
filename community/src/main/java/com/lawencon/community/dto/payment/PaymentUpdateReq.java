package com.lawencon.community.dto.payment;

import javax.validation.constraints.NotNull;

public class PaymentUpdateReq {

	private String id;

	private Boolean isActive;

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

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

}
