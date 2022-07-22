package com.lawencon.community.dto.user;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class UserUpdateReq {

	private String id;

	@NotBlank(message = "User Id can't be empty")
	private String userId;

	@NotBlank(message = "Role Id can't be empty")
	private String roleId;

	@NotBlank(message = "Verification Id can't be empty")
	private String verificationId;

	@NotNull(message = "Email can't be empty")
	private String email;

	@NotBlank(message = "Passwords can't be empty")
	private String passwords;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getVerificationId() {
		return verificationId;
	}

	public void setVerificationId(String verificationId) {
		this.verificationId = verificationId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPasswords() {
		return passwords;
	}

	public void setPasswords(String passwords) {
		this.passwords = passwords;
	}

}
