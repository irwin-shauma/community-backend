package com.lawencon.community.dto.user;

public class LoginData {
	private String id;
	private String email;
	private String token;
	private String refreshToken;
	private String roleCode;
	private Boolean premiumStatus;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public Boolean getPremiumStatus() {
		return premiumStatus;
	}

	public void setPremiumStatus(Boolean premiumStatus) {
		this.premiumStatus = premiumStatus;
	}

}
