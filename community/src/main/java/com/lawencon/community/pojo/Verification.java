package com.lawencon.community.pojo;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;

import com.lawencon.base.BaseEntity;

@Entity
public class Verification extends BaseEntity{
	private static final long serialVersionUID = -5196455701225322056L;
	
	@Column(name = "verification_code")
	private String verificationCode;
	private LocalDateTime expired;
	
	public String getVerificationCode() {
		return verificationCode;
	}
	
	public void setVerificationCode(String verificationCode) {
		this.verificationCode = verificationCode;
	}
	
	public LocalDateTime getExpired() {
		return expired;
	}
	public void setExpired(LocalDateTime expired) {
		this.expired = expired;
	}

	
	
	
}