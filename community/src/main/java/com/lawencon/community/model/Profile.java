package com.lawencon.community.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "profile")
public class Profile extends BaseEntity {
	private static final long serialVersionUID = -5196455701225322056L;

	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;

	@Column(name = "full_name")
	private String fullName;

	@Column(name = "company")
	private String company;
	
	@Column(name = "industry")
	private String industry;
	
	@Column(name = "position")
	private String position;
	
	@Column(name = "status")
	private String status;

	@Column(name = "status_duration")
	private LocalDateTime status_duration;

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

	public LocalDateTime getStatus_duration() {
		return status_duration;
	}

	public void setStatus_duration(LocalDateTime status_duration) {
		this.status_duration = status_duration;
	}

}
