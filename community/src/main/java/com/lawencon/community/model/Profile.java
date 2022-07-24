package com.lawencon.community.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "profile", uniqueConstraints = {
		@UniqueConstraint(name = "profile_code_bk", columnNames = "profile_code") })
public class Profile extends BaseEntity {
	private static final long serialVersionUID = -5196455701225322056L;

	@Column(name = "profile_code")
	private String profileCode;

	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;

	@Column(name = "full_name")
	private String fullName;

	@Column(name = "company")
	private String company;

	@Column(name = "industry")
	private String industry;

	@Column(name = "positions")
	private String position;

	@Column(name = "status")
	private String status;

	@OneToOne
	@JoinColumn(name = "file_id")
	private File file;

	@Column(name = "status_duration")
	private LocalDateTime statusDuration;

	public String getProfileCode() {
		return profileCode;
	}

	public void setProfileCode(String profileCode) {
		this.profileCode = profileCode;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public LocalDateTime getStatusDuration() {
		return statusDuration;
	}

	public void setStatusDuration(LocalDateTime statusDuration) {
		this.statusDuration = statusDuration;
	}

}
