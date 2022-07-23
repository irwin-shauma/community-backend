package com.lawencon.community.dto.premiumpaymenthistory;

public class PremiumPaymentHistoryData {

	private String id;
	private String premiumPaymentHistoryCode;
	private String userId;
	private String premiumTypeId;
	private String trxNo;
	private Boolean isActive;
	private Integer version;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPremiumPaymentHistoryCode() {
		return premiumPaymentHistoryCode;
	}
	public void setPremiumPaymentHistoryCode(String premiumPaymentHistoryCode) {
		this.premiumPaymentHistoryCode = premiumPaymentHistoryCode;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPremiumTypeId() {
		return premiumTypeId;
	}
	public void setPremiumTypeId(String premiumTypeId) {
		this.premiumTypeId = premiumTypeId;
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