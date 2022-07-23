package com.lawencon.community.dto.eventpaymenthistory;

import javax.validation.constraints.NotBlank;

public class EventPaymentHistoryInsertReq {
	
	@NotBlank(message = "User Id can't be empty")
	private String userId;
	
	@NotBlank(message = "Event Id can't be empty")
	private String eventHeaderId;
	
	@NotBlank(message = "trxNo can't be empty")
	private String trxNo;

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
	
	
	
}
