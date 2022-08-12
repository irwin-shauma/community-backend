package com.lawencon.community.dto.premiumpaymenthistory;

import javax.validation.constraints.NotNull;

public class PremiumPaymentHistoryUpdateReq {

	@NotNull(message = "ID can't be empty")
	private String id;

	@NotNull(message = "Active can't be empty")
	private Boolean isActive;

	@NotNull(message = "Payment can'be null")
	private String paymentId;

	@NotNull(message = "Approve can't be Empty")
	private Boolean isApprove;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public String getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}

	public Boolean getIsApprove() {
		return isApprove;
	}

	public void setIsApprove(Boolean isApprove) {
		this.isApprove = isApprove;
	}

}
