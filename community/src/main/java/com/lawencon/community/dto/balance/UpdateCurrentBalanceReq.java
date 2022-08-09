package com.lawencon.community.dto.balance;

import java.math.BigDecimal;

public class UpdateCurrentBalanceReq {
	private String userId;
	private BigDecimal balance;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

}
