package com.lawencon.community.dto.eventpaymenthistory;

public class EventPaymentHistoryData {

	private String id;
	private String eventPaymentHistoryCode;
	private String userId;
	private String eventHeaderId;
	private String trxNo;
	private Boolean isActive;
	private Integer version;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEventPaymentHistoryCode() {
		return eventPaymentHistoryCode;
	}

	public void setEventPaymentHistoryCode(String eventPaymentHistoryCode) {
		this.eventPaymentHistoryCode = eventPaymentHistoryCode;
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

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

}
