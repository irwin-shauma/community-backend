package com.lawencon.community.dto.balance;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class BalanceInsertReq {
	
	@NotNull(message = "Current balance can't be empty")
	private BigDecimal currentBalance;
	
	@NotBlank(message = "User Id can't be empty")
	private String userId;


	public String getUserId() {
		return userId;
	}

	public BigDecimal getCurrentBalance() {
		return currentBalance;
	}

	public void setCurrentBalance(BigDecimal currentBalance) {
		this.currentBalance = currentBalance;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
}
