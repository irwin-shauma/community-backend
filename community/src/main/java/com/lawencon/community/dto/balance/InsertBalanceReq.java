package com.lawencon.community.dto.balance;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class InsertBalanceReq {
	
	@NotBlank(message = "Balance Code can't be empty")
	@Size(min =3, max = 50, message = "Balance Code size must be between 3 to 50")
	private String balanceCode;
	
	@NotBlank(message = "current balance can't be empty")
	private Float currentBalance;
	
	@NotBlank(message = "user can't be empty")
	private String userId;

	public String getBalanceCode() {
		return balanceCode;
	}

	public void setBalanceCode(String balanceCode) {
		this.balanceCode = balanceCode;
	}

	public Float getCurrentBalance() {
		return currentBalance;
	}

	public void setCurrentBalance(Float currentBalance) {
		this.currentBalance = currentBalance;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
}
