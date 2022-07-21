package com.lawencon.community.dto.user;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class UserInsertReq {
	
	@NotBlank(message = "Role Id can't be empty")
	private String roleId;
	
	@NotBlank(message = "Verification Id can't be empty")
	private String verificationId;
	
	@NotNull(message = "Email can't be empty")
	private String email;
	
	@NotBlank(message = "Passwords can't be empty")
	private String passwords;

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
