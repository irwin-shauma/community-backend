package com.lawencon.community.dto.historypayment;

public class HistoryPaymentData {
	
	private String id;
	private String historyPaymentCode;
	private String userId;
	private String trxNo;
	private Boolean isActive;
	private Integer version;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getHistoryPaymentCode() {
		return historyPaymentCode;
	}
	public void setHistoryPaymentCode(String historyPaymentCode) {
		this.historyPaymentCode = historyPaymentCode;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
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
