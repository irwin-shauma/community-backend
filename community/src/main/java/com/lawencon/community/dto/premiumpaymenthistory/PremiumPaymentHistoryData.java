package com.lawencon.community.dto.premiumpaymenthistory;

import java.time.LocalDateTime;

public class PremiumPaymentHistoryData {
	private String id;
	private String premiumPaymentHistoryCode;
	private String userId;
	private String premiumTypeId;
	private String paymentId;

	private String fullname;
	private float price;
	private Integer duration;
	private LocalDateTime createdAt;
	private String trxNo;
	private Boolean isActive;
	private Integer version;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
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

	public String getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
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
