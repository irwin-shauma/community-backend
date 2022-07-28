package com.lawencon.community.dto.premiumpaymenthistory;

import javax.validation.constraints.NotBlank;

public class PremiumPaymentHistoryInsertReq {

	@NotBlank(message = "User Id can't be empty")
	private String userId;

	@NotBlank(message = "Premium Id can't be empty")
	private String premiumTypeId;

	@NotBlank(message = "trxNo can't be empty")
	private String trxNo;

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

	
	

}
