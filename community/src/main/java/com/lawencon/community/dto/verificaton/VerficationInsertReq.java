package com.lawencon.community.dto.verificaton;

import java.time.LocalDateTime;

public class VerficationInsertReq {
	
	private String verification;
	private LocalDateTime expired;
	public String getVerification() {
		return verification;
	}
	public void setVerification(String verification) {
		this.verification = verification;
	}
	public LocalDateTime getExpired() {
		return expired;
	}
	public void setExpired(LocalDateTime expired) {
		this.expired = expired;
	}

}
