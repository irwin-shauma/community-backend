package com.lawencon.community.dto.user;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class UserInsertReq {

	private String roleId;

	@NotBlank(message = "Verification Id can't be empty")
	private String verificationId;

	@NotNull(message = "Email can't be empty")
	private String email;

	@NotBlank(message = "Password can't be empty")
	private String password;

	@NotBlank(message = "Fullname can't be empty")
	private String fullName;

	@NotBlank(message = "Company can't be empty")
	private String company;

	@NotBlank(message = "Industry can't be empty")
	private String industry;

	@NotBlank(message = "Position can't be empty")
	private String position;

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

}
