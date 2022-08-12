package com.lawencon.community.dto.eventpaymenthistory;

import javax.validation.constraints.NotNull;

public class EventPaymentHistoryUpdateReq {

	@NotNull(message = "ID can't be empty")
	private String id;

	@NotNull(message = "Payment Id can't be empty")
	private String paymentId;

	@NotNull(message = "Approve can't be Empty")
	private Boolean isApprove;

	@NotNull(message = "Active can't be empty")
	private Boolean isActive;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
}
