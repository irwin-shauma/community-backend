package com.lawencon.community.dto.historypayment;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class InsertHistoryPaymentReq {
	
	@NotBlank(message = "Balance Code can't be empty")
	@Size(min =3, max = 50, message = "Balance Code size must be between 3 to 50")
	private String historyPaymentCode;
	
	@NotBlank(message = "user can't be empty")
	private String userId;
	
	@NotBlank(message = "trxNo can't be empty")
	private String trxNo;

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
	
	
	
}
