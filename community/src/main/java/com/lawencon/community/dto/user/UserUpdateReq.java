package com.lawencon.community.dto.user;

import javax.validation.constraints.NotBlank;

public class UserUpdateReq {

	private String id;

	@NotBlank(message = "Fullname can't be empty")
	private String fullName;

	@NotBlank(message = "Company can't be empty")
	private String company;

	@NotBlank(message = "Industry can't be empty")
	private String industry;

	@NotBlank(message = "Position can't be empty")
	private String position;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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
