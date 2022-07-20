package com.lawencon.community.dto.balance;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UpdateBalanceReq {
	
	@NotNull(message = "ID can't be empty")
	@Min(value = 1, message = "Minimum ID must be greater than 0")
	private String id;
	
	@NotBlank(message = "Balance code can't be empty")
	@Size(min = 3, max = 50, message = "Balance Code must be between 3 to 50")
	private String balanceCode;
	
	@NotBlank(message = "Current Balance can't be empty")
	private Float currentBalance;
	
	@NotBlank(message = "User can't be empty")
	private String userId;
	
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
