package com.lawencon.community.dto.user;

import javax.validation.constraints.NotBlank;

public class UserChangePasswordReq {
	
	@NotBlank(message = "Old password can't be empty")
	private String oldPassword;
	
	@NotBlank(message = "New password can't be empty")
	private String newPassword;
	
	public String getOldPassword() {
		return oldPassword;
	}
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
}
