package com.lawencon.community.dto;

import java.math.BigDecimal;

public class RevenueReport {
	private String fullName;
	private BigDecimal balance;
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public BigDecimal getBalance() {
		return balance;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
}
