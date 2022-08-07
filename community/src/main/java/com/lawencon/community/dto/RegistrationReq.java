package com.lawencon.community.dto;

import javax.validation.constraints.NotBlank;

public class RegistrationReq {
	
	@NotBlank(message = "Email can't be empty")
	private String email;
	
	@NotBlank(message = "Verification Code can't be empty")
	private String verificationCode;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getVerificationCode() {
		return verificationCode;
	}
	public void setVerificationCode(String verificationCode) {
		this.verificationCode = verificationCode;
	}
	
	

}
