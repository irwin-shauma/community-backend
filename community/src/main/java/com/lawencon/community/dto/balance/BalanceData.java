package com.lawencon.community.dto.balance;

public class BalanceData {
	
	private String id;
	private String balanceCode;
	private Float currentBalance;
	private String userId;
	private Boolean isActive;
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
