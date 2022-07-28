package com.lawencon.community.dto.balance;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class BalanceUpdateReq {
	
	@NotNull(message = "ID can't be empty")
	private String id;
	
	@NotNull(message = "Current Balance can't be empty")
	private Float currentBalance;
	
	@NotBlank(message = "User Id can't be empty")
	private String userId;
	
	@NotNull(message = "Active can't be empty")
	private Boolean isActive;
	
	@NotNull(message = " version can't be empty")
	private Integer version;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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
