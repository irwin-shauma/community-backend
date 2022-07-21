package com.lawencon.community.dto.historypayment;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class HistoryPaymentUpdateReq {
	
	@NotNull(message = "ID can't be empty")
	private String id;
	
	@NotBlank(message = "History Payment Code can't be empty")
	@Size(min = 3, max = 50, message = "History Payment Code must be between 3 to 50")
	private String historyPaymentCode;
	
	@NotBlank(message = "User Id can't be empty")
	private String userId;
	
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
