package com.lawencon.community.dto.premiumpaymenthistory;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PremiumPaymentHistoryUpdateReq {

	@NotNull(message = "ID can't be empty")
	private String id;

	@NotBlank(message = "User Id can't be empty")
	private String userId;

	@NotBlank(message = "Premium Id can't be empty")
	private String premiumTypeId;

	@NotBlank(message = "Trx no can't be empty")
	private String trxNo;

	@NotBlank(message = "Active can't be empty")
	private Boolean isActive;

	@NotBlank(message = " version can't be empty")
	private Integer version;

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
