package com.lawencon.community.dto.balance;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class BalanceUpdateReq {

	@NotNull(message = "ID can't be empty")
	private String id;

	@NotNull(message = "Current Balance can't be empty")
	private BigDecimal currentBalance;

	@NotBlank(message = "User Id can't be empty")
	private String userId;

	@NotNull(message = "Active can't be empty")
	private Boolean isActive;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public BigDecimal getCurrentBalance() {
		return currentBalance;
	}

	public void setCurrentBalance(BigDecimal currentBalance) {
		this.currentBalance = currentBalance;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
}
