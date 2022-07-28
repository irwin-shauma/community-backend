package com.lawencon.community.dto.eventpaymenthistory;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class EventPaymentHistoryUpdateReq {

	@NotNull(message = "ID can't be empty")
	private String id;

	@NotBlank(message = "User Id can't be empty")
	private String userId;

	@NotBlank(message = "Event Id can't be empty")
	private String eventHeaderId;

	@NotBlank(message = "Trx no can't be empty")
	private String trxNo;

	@NotBlank(message = "Active can't be empty")
	private Boolean isActive;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getEventHeaderId() {
		return eventHeaderId;
	}

	public void setEventHeaderId(String eventHeaderId) {
		this.eventHeaderId = eventHeaderId;
	}

	public String getTrxNo() {
		return trxNo;
	}

	public void setTrxNo(String trxNo) {
		this.trxNo = trxNo;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
}
